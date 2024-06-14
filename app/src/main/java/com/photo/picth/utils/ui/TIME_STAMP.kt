package com.photo.picth.utils.ui

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import com.photo.picth.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val TIME_STAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"


object FilePathUtil {

    fun getMediaFilePathFor(
        context: Context, uri: Uri, compression: Boolean = true, width: Int = 512, height: Int = 512
    ): File? {
        val returnCursor = context.contentResolver.query(uri, null, null, null, null) ?: return null
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val file = File(context.filesDir, name)
        try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val outputStream = FileOutputStream(file)
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable = inputStream.available()
            val bufferSize = bytesAvailable.coerceAtMost(maxBufferSize)
            val buffers = ByteArray(bufferSize)
            var read: Int
            while (inputStream.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            inputStream.close()
            outputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        returnCursor.close()
        return if (compression) {
            val compressed = getCompressed(context, file.path, width, height)
            deleteFile(file)
            compressed
        } else file
    }

    fun getPathFromUri(context: Context, uri: Uri): String? {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
                // TODO handle non-primary volumes
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri: Uri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                when (type) {
                    "image" -> {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    }

                    "video" -> {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    }

                    "audio" -> {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(
                    split[1]
                )
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
                context, uri, null, null
            )
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    private fun getDataColumn(
        context: Context, uri: Uri?, selection: String?,
        selectionArgs: Array<String>?,
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(
            column
        )
        try {
            cursor = context.contentResolver.query(
                uri!!, projection, selection, selectionArgs, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun isExternalStorageDocument(uri: Uri): Boolean =
        "com.android.externalstorage.documents" == uri.authority

    private fun isDownloadsDocument(uri: Uri): Boolean =
        "com.android.providers.downloads.documents" == uri.authority

    private fun isMediaDocument(uri: Uri): Boolean =
        "com.android.providers.media.documents" == uri.authority

    private fun isGooglePhotosUri(uri: Uri): Boolean =
        "com.google.android.apps.photos.content" == uri.authority

    /*fun convertFileToContentUri(context: Context, file: File,returnUri:(Uri?)->Unit) {
        val values = ContentValues()
        values.put(MediaStore.Video.Media.TITLE, "Title1")
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
        values.put(MediaStore.Video.Media.DATA, file.absolutePath)
        val uri: Uri = cr.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)
    }*/

    fun saveImage(
        context: Context,
        myBitmap: Bitmap,
        compression: Boolean = true,
        width: Int = 512,
        height: Int = 512,
        name: String? = null,
        inBuildFolder: String? = null
    ): File? {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val imagesDirectory = File(
            context.cacheDir.absolutePath + File.separator + context.getString( R.string.app_name) + (inBuildFolder
                ?: "")
        )

        if (!imagesDirectory.exists()) imagesDirectory.mkdirs()

        try {
            val createdFile = File(
                imagesDirectory, ((name ?: SimpleDateFormat(
                    TIME_STAMP, Locale.getDefault()
                ).format(Date())).toString() + ".jpeg")
            )
            createdFile.createNewFile()
            createdFile.writeBytes(bytes.toByteArray())

            return if (compression) {
                val file = getCompressed(
                    context,
                    createdFile.absolutePath,
                    width = width,
                    height = height,
                )
                deleteFile(createdFile)
                file
            } else createdFile
        } catch (e1: IOException) {
            e1.printStackTrace()
            return null
        }
    }

    private fun getCompressed(
        context: Context,
        path: String?,
        width: Int,
        height: Int,
    ): File {
        val cacheDir = context.cacheDir
        val rootDir = cacheDir.absolutePath + File.separator + context.getString( R.string.app_name)

        val root = File(rootDir)
        if (!root.exists()) root.mkdirs()

        val bitmap = compressImage(
            path,
            width,
            height,
        )

        val compressed = File(
            root,
            SimpleDateFormat(TIME_STAMP, Locale.getDefault()).format(Date())
                .toString() + ".jpeg"
        )

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        /*Right now, we have our bitmap inside byteArrayOutputStream Object, all we need next is to write it to the compressed file we created earlier,
        java.io.FileOutputStream can help us do just That!
         */

        val fileOutputStream = FileOutputStream(compressed)
        fileOutputStream.write(byteArrayOutputStream.toByteArray())
        fileOutputStream.flush()
        fileOutputStream.close()
        return compressed
    }

    private fun compressImage(path: String?, width: Int, height: Int): Bitmap? {
        if (path.isNullOrBlank()) return null
        val scaleOptions = BitmapFactory.Options()
        scaleOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, scaleOptions)
        var scale = 1
        while (scaleOptions.outWidth / scale / 2 >= width && scaleOptions.outHeight / scale / 2 >= height) {
            scale *= 2
        }
        // decode with the sample size
        val outOptions = BitmapFactory.Options()
        outOptions.inSampleSize = scale
        return rotateImageIfRequired(path, outOptions)
    }

    fun String.getFileType(): String = try {
        File(this).extension
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }

    fun deleteFile(file: File) {
        if (file.exists()) file.delete()
    }

    fun deleteFile(fileLocation: String?) {
        try {
            val file = File(fileLocation ?: return)
            if (file.exists()) file.delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getExtension(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf("."))
    }

    fun rotateImageIfRequired(
        selectedImage: String?,
        outOptions: BitmapFactory.Options,
    ): Bitmap? {
        if (selectedImage.isNullOrBlank()) return null
        val ei = ExifInterface(selectedImage)
        val orientation: Int = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED
        )
        val img = BitmapFactory.decodeFile(selectedImage, outOptions)
        var rotatedBitmap: Bitmap = img

        when (orientation) {
            ExifInterface.ORIENTATION_NORMAL -> {
                Log.e("orientation is", "orientation 0")
                rotatedBitmap = rotateImage(img, 0)
            }

            ExifInterface.ORIENTATION_ROTATE_90 -> {
                Log.e("orientation is", "orientation 90")
                rotatedBitmap = rotateImage(img, 90)
            }

            ExifInterface.ORIENTATION_ROTATE_180 -> {
                Log.e("orientation is", "orientation 180")
                rotatedBitmap = rotateImage(img, 180)
            }

            ExifInterface.ORIENTATION_ROTATE_270 -> {
                Log.e("orientation is", "orientation 270")
                rotatedBitmap = rotateImage(img, 270)
            }
        }
        return rotatedBitmap
    }

    private fun rotateImage(bitmap: Bitmap, degrees: Int): Bitmap =
        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, Matrix().apply {
            postRotate(degrees.toFloat())
        }, true)

}