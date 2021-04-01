package com.gsgroup.hrapp.ui.fragment.requests.myrequest.toreply

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentRequestsToReplyBinding

class RequestsToReplyFragment :
    BaseFragment<FragmentRequestsToReplyBinding, RequestsToReplyViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: RequestsToReplyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}