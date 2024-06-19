package com.photo.picth.utils.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.fragment.app.FragmentActivity

class PermissionHelper(private val activity: FragmentActivity, private val callback: PermissionHelperCallback) {

    interface PermissionHelperCallback {

        fun onPermissionGranted(permissions: Map<String, Boolean>)

        fun onRequestDeniedPermission(deniedPermissionsList: List<String>)

        fun onRequestCancel()
    }

    fun hasPermissionDefinedInManifest(context: Context, permissions: Array<String>): Boolean {
        val iterator = permissions.iterator()
        iterator.forEach { permission ->
            if (!hasPermissionInManifest(context, permission)) {
                println("please declare permission $permission in manifest")
                return false
            }
        }
        return true
    }

    private fun hasPermissionInManifest(context: Context, permission: String): Boolean {
        try {
            val info: PackageInfo = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_PERMISSIONS
            )
            if (info.requestedPermissions != null) {
                for (p in info.requestedPermissions) {
                    if (p == permission) {
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun computeResult(result: Map<String, Boolean>) {
        val allGranted = result.all { entry: Map.Entry<String, Boolean> -> entry.value }
        if (allGranted) {
            callback.onPermissionGranted(result)
        } else {
            val deniedList = result.filter { entry -> !entry.value }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val shouldRequestAgain = deniedList.any { entry ->
                    shouldShowRequestPermissionRationale(activity, entry.key)
                }
                if (shouldRequestAgain) {
                    val deniedListNew = arrayListOf<String>()
                    deniedList.forEach {
                        if (!it.value)
                            deniedListNew.add(it.key)
                    }
                    showRequestReasonDialog(deniedListNew)
                } else {
                    showEnablePermissionInSettingManuallyDialog()
                }
            }
        }
    }

    private fun showRequestReasonDialog(deniedPermissionsList: List<String>) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Permissions Required")
        builder.setMessage("We need permissions to process further")

        builder.setPositiveButton("Allow") { dialogInterface, _ ->
            dialogInterface.dismiss()
            callback.onRequestDeniedPermission(deniedPermissionsList)
        }
        builder.setNegativeButton("Deny") { dialogInterface, _ ->
            dialogInterface.dismiss()
            showEnablePermissionInSettingManuallyDialog()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun showEnablePermissionInSettingManuallyDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Enable Permissions manually")
        builder.setMessage("Need to allow necessary permissions in Settings manually.")

        builder.setPositiveButton("Open Settings") { dialogInterface, _ ->
            dialogInterface.dismiss()
            openAppSetting()
        }
        builder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.dismiss()
            callback.onRequestCancel()
            //ToastUtils.showToast("Unable to perform action due to permissions")
           // showShortToast("Unable to perform action due to permissions")
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun openAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", activity.packageName, null)
        }
        activity.startActivity(intent)
    }

}