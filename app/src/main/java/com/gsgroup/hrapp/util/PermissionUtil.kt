package com.gsgroup.hrapp.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import timber.log.Timber

object PermissionUtil {
    fun isGranted(activity: Context, permission: String): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        } else true
    }

    enum class AppPermissionResult {
        ALL_GOOD, OPEN_SETTING, FAIL
    }

    private fun Context.runDexter(
        permissions: List<String>,
        callBack: (AppPermissionResult) -> Unit
    ) {
        Dexter.withContext(this)
            .withPermissions(permissions).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    when {
                        report.areAllPermissionsGranted() -> {
                            callBack(AppPermissionResult.ALL_GOOD)
                        }
                        report.isAnyPermissionPermanentlyDenied -> {
                            callBack(AppPermissionResult.OPEN_SETTING)
                        }
                        else -> {
                            callBack(AppPermissionResult.FAIL)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    Timber.e("hg")
                    token.continuePermissionRequest()
                }
            }).check()
    }

    fun Context.requestAppPermission(
        permission: String,
        callBack: (AppPermissionResult) -> Unit
    ) {
        val list = listOf(permission)
        runDexter(list, callBack)
    }

    fun Context.goToSettingsPermissions(
        msg: String,
        register: ActivityResultLauncher<Intent>?
    ) {
        showDialog(msg) {
            val myAppSettings = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:$packageName")
            )
            myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
            register?.launch(myAppSettings)
        }
    }

    fun Context.requestAppPermissions(
        permissions: List<String>,
        callBack: (AppPermissionResult) -> Unit
    ) {
        runDexter(permissions, callBack)
    }

    fun hasAllLocationPermissions(context: Context, includeBGLocation: Boolean = false): Boolean {
        return if (includeBGLocation) {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                isGranted(context, Manifest.permission.ACCESS_FINE_LOCATION) &&
                        isGranted(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            } else {
                isGranted(context, Manifest.permission.ACCESS_FINE_LOCATION)
            }
        } else
            isGranted(context, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun hasImagePermission(fragmentActivity: FragmentActivity): Boolean {
        return if (isGranted(fragmentActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (isGranted(fragmentActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                isGranted(fragmentActivity, Manifest.permission.CAMERA)
            } else {
                false
            }
        } else {
            false
        }
    }

    fun getAllImagePermissions(): List<String> {
        return listOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
    }

    fun getAllLocationPermissions(includeBGLocation: Boolean = false): List<String> {
        val permissionList = mutableListOf(Manifest.permission.ACCESS_FINE_LOCATION)
        if (includeBGLocation)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        return permissionList
    }

    fun getPhoneCriticalPermissions(): List<String> {
        val permissionList = mutableListOf(Manifest.permission.READ_PHONE_STATE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            permissionList.add(Manifest.permission.READ_PRECISE_PHONE_STATE)
        }
        return permissionList
    }

}