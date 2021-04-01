package com.gsgroup.hrapp.ui.fragment.requests.myrequest.show

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentRequestsToReplyBinding

class MyRequestsFragment :
    BaseFragment<FragmentMyRequestsBinding, MyRequestsViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: MyRequestsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}