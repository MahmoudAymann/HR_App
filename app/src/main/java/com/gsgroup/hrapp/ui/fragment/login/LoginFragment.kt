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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.HOME_SCREEN -> showMainActivity()
                    Codes.RESTART_APP -> activity?.restartApp()
                    Codes.CHANGE_LANG -> activity?.showDialog(getString(R.string.ask_change_language)) { mViewModel.changeLang() }
                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        activity?.showSuccessfulDialog(it.message) {
                                showMainActivity()
                        }
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

    override val mViewModel: LoginViewModel by viewModels()



}