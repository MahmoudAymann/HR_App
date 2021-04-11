package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentSignInOutLogBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe

class SignInOutLogFragment : BaseFragment<FragmentSignInOutLogBinding, SignInOutLogViewModel>() {
    override fun pageTitle(): String = getString(R.string.sign_in_out_logs)
    override val mViewModel: SignInOutLogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {

        }

    }

}