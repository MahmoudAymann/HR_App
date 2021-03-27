package com.gsgroup.hrapp.ui.fragment.requests.exp_certificate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentExpCertificateBinding

class ExpCertificateFragment :
    BaseFragment<FragmentExpCertificateBinding, ExpCertificateViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: ExpCertificateViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}