package com.gsgroup.hrapp.util

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.gsgroup.hrapp.R

fun FragmentActivity.showLogoutDialog(onClick: () -> Unit) {
    val dialog = SweetAlertDialog(this)
        .setContentText(getString(R.string.log_out_check_message))
        .setConfirmButton(getString(R.string.yes)) {
            it.closeDialog()
            onClick()
        }
        .setCancelButton(getString(R.string.no)) { sDialog ->
            sDialog.closeDialog()
        }
        .setConfirmButtonBackgroundColor(getColorFromRes(R.color.red))
        .setConfirmButtonTextColor(getColorFromRes(R.color.white))
        .setCancelButtonBackgroundColor(getColorFromRes(R.color.white))
    dialog.show()
}

fun Activity.showExitDialog() {
    val dialog = SweetAlertDialog(this)
        .setContentText(getString(R.string.exit_app))
        .setConfirmButton(getString(R.string.exit)) {
            it.closeDialog()
            finishAffinity()
        }
        .setCancelButton(getString(R.string.cancel)) { sDialog ->
            sDialog.closeDialog()
        }
        .setConfirmButtonBackgroundColor(getColorFromRes(R.color.colorPrimary))
        .setConfirmButtonTextColor(getColorFromRes(R.color.white))
        .setCancelButtonBackgroundColor(getColorFromRes(R.color.white))
    dialog.show()
}

fun Context.showDialog(msg: String?, type: Int? = null, okClick: () -> Unit = {}) {
    SweetAlertDialog(
        this,
        type ?: SweetAlertDialog.NORMAL_TYPE
    )
        .setContentText(msg)
        .setConfirmButton(getString(R.string.yes)) { sDialog ->
            sDialog.closeDialog()
            okClick()
        }.setCancelButton(getString(R.string.no)) {
            it.closeDialog()
        }
        .setConfirmButtonBackgroundColor(getColorFromRes(R.color.black))
        .setConfirmButtonTextColor(getColorFromRes(R.color.white))
        .show()
}

fun Context.showWarningDialog(message:String, okClick: () -> Unit = {}){
    SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        .setContentText(message)
        .setConfirmButton(getString(R.string.continue_)){
            it.closeDialog()
            okClick.invoke()
        }.setConfirmButtonBackgroundColor(getColorFromRes(R.color.black))
        .setConfirmButtonTextColor(getColorFromRes(R.color.white))
        .show()
}

fun SweetAlertDialog.closeDialog() {
    if (!AppUtil.isOldDevice())
        dismissWithAnimation()
    else
        dismiss()
}




