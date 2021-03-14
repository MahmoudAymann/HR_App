package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentSignInOutLogBinding

class SignInOutLog : BaseFragment<FragmentSignInOutLogBinding, SignInOutLogViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: SignInOutLogViewModel by viewModels()
}