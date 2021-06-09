package com.gsgroup.hrapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.*
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.ui.fragment.login.LoginFragment
import com.gsgroup.hrapp.util.SharedPrefUtil.setData
import com.gsgroup.hrapp.util.SharedPrefUtil.sharedPrefs
import timber.log.Timber
import java.util.concurrent.Executor

object BiometricUtils {
    private const val TAG = "BiometricUtils"

    fun isAvailable(ctx: FragmentActivity, setResult: (Int) -> Unit) {
        if (AppUtil.isOldDevice()) {
            setResult(BiometricCodes.FAIL_USE_BIOMETRIC)
            return
        }
        val bioMgr: BiometricManager = from(ctx)
        when (bioMgr.canAuthenticate(Authenticators.BIOMETRIC_WEAK)) {
            BIOMETRIC_SUCCESS -> {
                Timber.tag(TAG).e("App can use biometrics.")
                setResult(BiometricCodes.CAN_USE_BIOMETRIC)
            }
            BIOMETRIC_ERROR_NO_HARDWARE -> {
                Timber.tag(TAG).e("No biometric features available on this device.")
                setResult(BiometricCodes.FAIL_USE_BIOMETRIC)
            }
            BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Timber.tag(TAG).e("Biometric features are currently unavailable.")
                setResult(BiometricCodes.FAIL_USE_BIOMETRIC)
            }
            BIOMETRIC_ERROR_NONE_ENROLLED -> {
                setResult(BiometricCodes.FAIL_USE_BIOMETRIC)
            }
            else -> setResult(BiometricCodes.FAIL_USE_BIOMETRIC)
        }
    }


    fun showBiometric(
        context: FragmentActivity,
        processSuccess: () -> Unit
    ) {
        lateinit var biometricPrompt: BiometricPrompt
        val executor: Executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(context, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Timber.tag(TAG).e("auth error: $errString")
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    processSuccess.invoke()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Timber.tag(TAG).e("auth failed please login normal")
                }
            })

        biometricPrompt.authenticate(getPromptInfo(context))
    }

    private fun getPromptInfo(context: Context): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(context.getString(R.string.biometric_login))
            .setSubtitle(context.getString(R.string.login_fast))
            .setNegativeButtonText(context.getString(R.string.not_now))
            .build()
    }


    fun showAskToUseDialog(
        fragment: BaseFragment<*, *>,
        saveDataCallBack: () -> Unit,
        continueCallBack: () -> Unit
    ) {
        val dialog = SweetAlertDialog(fragment.context, SweetAlertDialog.NORMAL_TYPE)
            .setContentText(fragment.getString(R.string.biometric_ask_to_use_next_login))
            .setConfirmButton(fragment.getString(R.string.yes)) {
                it.closeDialog()
                saveDataCallBack.invoke()
            }
            .setConfirmButtonBackgroundColor(
                fragment.requireActivity().getColorFromRes(R.color.colorAccent)
            )
            .setCancelButton(fragment.requireActivity().getString(R.string.no)) {
                it.closeDialog()
                continueCallBack.invoke()
            }
            .setNeutralButton(fragment.getString(R.string.dont_ask_again)) {
                it.closeDialog()
                fragment.requireActivity().sharedPrefs<String>(ConstString.PREF_DONT_ASK_AGAIN_BIO)
                    .setData("true")
                continueCallBack.invoke()
            }
        dialog.show()
    }

    object BiometricCodes {
        const val REQUEST_CODE = 123456
        const val CAN_USE_BIOMETRIC = 1
        const val FAIL_USE_BIOMETRIC = 0
    }


}