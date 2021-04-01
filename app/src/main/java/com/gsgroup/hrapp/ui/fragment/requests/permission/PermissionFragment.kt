package com.gsgroup.hrapp.ui.fragment.requests.permission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentPermissionBinding

class PermissionFragment : BaseFragment<FragmentPermissionBinding, PermissionViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: PermissionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}