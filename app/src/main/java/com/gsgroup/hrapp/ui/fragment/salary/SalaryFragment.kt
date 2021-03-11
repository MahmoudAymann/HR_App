package com.gsgroup.hrapp.ui.fragment.salary

import android.os.Bundle
import android.view.View
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentLoginBinding
import com.gsgroup.hrapp.ui.fragment.home.HomeFragment
import com.gsgroup.hrapp.ui.fragment.login.LoginViewModel
import com.gsgroup.hrapp.util.bindViewModel
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.viewBinding

class SalaryFragment : BaseFragment(R.layout.fragment_login) {
    override fun pageTitle(): String = getString(R.string.login)

    private val binding by viewBinding(FragmentLoginBinding::bind)

    lateinit var viewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = bindViewModel(binding) {
            observe(mutableLiveData) {
                when (it) {
                    Codes.HOME_SCREEN -> replaceFragment<HomeFragment>()
                }
            }
        }
    }


}