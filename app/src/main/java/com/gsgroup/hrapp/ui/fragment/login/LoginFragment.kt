package com.gsgroup.hrapp.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentLoginBinding
import com.gsgroup.hrapp.ui.fragment.home.HomeFragment
import com.gsgroup.hrapp.util.bindViewModel
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.viewBinding

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun pageTitle(): String = getString(R.string.login)

    lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.HOME_SCREEN -> replaceFragment<HomeFragment>()
                }
            }
        }
    }

    override val mViewModel: LoginViewModel by viewModels()


}