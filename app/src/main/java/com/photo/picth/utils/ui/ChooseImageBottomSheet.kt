package com.photo.picth.utils.ui


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.photo.picth.R
import com.photo.picth.databinding.ChooseBottomSheetLayoutBinding
import de.hdodenhof.circleimageview.BuildConfig
import java.io.File


class ChooseImageBottomSheet(private val selectedImage: (String) -> Unit) :
    BottomSheetDialogFragment(), PermissionHelper.PermissionHelperCallback {

    private var _binding: ChooseBottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NewApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireActivity(), R.style.Theme_Dialog)
        // on below line we are inflating a layout file which we have created.
        _binding = ChooseBottomSheetLayoutBinding.inflate(layoutInflater)

        dialog.window?.setGravity(Gravity.BOTTOM)

        binding.tvCamera.setOnClickListener {
            onImageSelectTypeClick("Camera")
        }
        binding.tvGallery.setOnClickListener {
            onImageSelectTypeClick("Gallery")
        }



        val view = binding.root
        dialog.setContentView(view)

        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)


        //setting Peek at the 16:9 ratio key line of its parent.
        bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        // bottomSheetDialog!!.window?.attributes = layoutParams

        dialog.show()
        permissionHelper = PermissionHelper(requireActivity(), this)
        return dialog
    }


    private lateinit var permissionHelper: PermissionHelper


    private fun onImageSelectTypeClick(type: String) {
        if (type == "Gallery") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                pickMultipleVisualMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                imageGalleryResult.launch(
                    Intent.createChooser(
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                        getString(R.string.select_picture)
                    )
                )
            }
        } else {
            val permissions = listOf(Manifest.permission.CAMERA)
            permissionsRequestLauncher.launch(permissions.toTypedArray())
        }
    }


    private var permissionsRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            permissionHelper.computeResult(it)
        }

    private var imageGalleryResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK && result.data == null) return@registerForActivityResult
            val file = FilePathUtil.getMediaFilePathFor(
                requireContext(),
                result.data?.data ?: Uri.parse(""),
            )
            file?.path?.toUri()?.let { launchImageCrop(it) }
        }

    private var pickMultipleVisualMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uris ->
            uris?.let {
                launchImageCrop(it)
            }
        }

    override fun onPermissionGranted(permissions: Map<String, Boolean>) {
        takePhotoFromCamera()
    }

    override fun onRequestDeniedPermission(deniedPermissionsList: List<String>) {
        permissionsRequestLauncher.launch(deniedPermissionsList.toTypedArray())
    }

    override fun onRequestCancel() = Unit


    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (!result.isSuccessful) return@registerForActivityResult
        val uriFilePath =
            result.getUriFilePath(requireContext()) ?: return@registerForActivityResult

        selectedImage(uriFilePath)
        dismiss()
    }

    private fun launchImageCrop(uri: Uri) {
        val blackColor = ContextCompat.getColor(requireContext(),R.color.black)
        val whiteColor = ContextCompat.getColor(requireContext(),R.color.white)

        val transparentColor = ContextCompat.getColor(requireContext(),android.R.color.transparent)

        cropImage.launch(
            CropImageContractOptions(
                uri = uri, cropImageOptions = CropImageOptions(
                    showCropLabel = true,
                    showCropOverlay = true,
                    backgroundColor = transparentColor,
                    activityBackgroundColor = transparentColor,
                    toolbarColor = blackColor,
                    toolbarTintColor = blackColor,
                    toolbarTitleColor = whiteColor,
                    toolbarBackButtonColor = whiteColor,
                    cropperLabelTextColor = whiteColor,
                    activityMenuTextColor = whiteColor,
                    activityMenuIconColor = whiteColor,
                    fixAspectRatio = true,
                    aspectRatioX = 1,
                    aspectRatioY = 1,
                    guidelines = CropImageView.Guidelines.ON,
                    outputCompressFormat = Bitmap.CompressFormat.PNG
                )
            )
        )
    }


    private val cameraRequestLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != Activity.RESULT_OK) return@registerForActivityResult
            cameraFileUri?.let { it1 -> launchImageCrop(it1) }
        }

    private var cameraFileUri: Uri? = null
    private fun takePhotoFromCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val mImageFile = File(
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS + "/attachments")!!.path,
            System.currentTimeMillis().toString() + ".jpg"
        )

        cameraFileUri = FileProvider.getUriForFile(
            requireActivity(), BuildConfig.APPLICATION_ID + ".provider", mImageFile
        )
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraFileUri)

        cameraRequestLauncher.launch(takePictureIntent)
    }

}