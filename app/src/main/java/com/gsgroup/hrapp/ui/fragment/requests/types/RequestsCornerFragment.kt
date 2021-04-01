package com.gsgroup.hrapp.ui.fragment.requests.types

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentRequestsCornerBinding

class RequestsCornerFragment :
    BaseFragment<FragmentRequestsCornerBinding, RequestsCornerViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: RequestsCornerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}