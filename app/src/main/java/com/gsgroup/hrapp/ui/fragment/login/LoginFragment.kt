package com.gsgroup.hrapp.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentLoginBinding
import com.gsgroup.hrapp.util.*

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun pageTitle(): String = getString(R.string.login)
    override val mViewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.HOME_SCREEN -> showMainActivity()
                    Codes.RESTART_APP -> activity?.restartApp()
                    Codes.CHANGE_LANG -> activity?.showDialog(getString(R.string.ask_change_language)) { mViewModel.changeLang() }
                    Codes.SHOW_BIOMETRIC_ASK_DIALOG-> BiometricUtils.showAskToUseDialog(this@LoginFragment,{
                        saveBiometricData()
                        showMainActivity()
                    }){
                        showMainActivity()
                    }
                    Codes.LOGIN_WITH_BIOMETRIC-> BiometricUtils.showBiometric(requireActivity()){
                        loginWithBiometricData()
                    }
                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                    }
                    Status.MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    Status.LOADING -> showProgress()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkForBiometricAvailability()
    }


    private fun checkForBiometricAvailability() {
        BiometricUtils.isAvailable(requireActivity()) { mViewModel.isBiometricHardwareAvail(it) }
    }
}