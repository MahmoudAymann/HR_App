package com.gsgroup.hrapp.ui.fragment.requests.phoneissue

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentPhoneIssueBinding

class PhoneIssueFragment : BaseFragment<FragmentPhoneIssueBinding, PhoneIssueViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: PhoneIssueViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}