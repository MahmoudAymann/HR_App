package com.gsgroup.hrapp.ui.fragment.requests.autharea

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentAuthAreaBinding

class AuthAreaFragment : BaseFragment<FragmentAuthAreaBinding, AuthAreaViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: AuthAreaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}