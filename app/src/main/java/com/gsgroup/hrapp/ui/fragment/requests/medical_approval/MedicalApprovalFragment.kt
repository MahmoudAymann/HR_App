package com.gsgroup.hrapp.ui.fragment.requests.medical_approval

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentMedicalApprovalBinding

class MedicalApprovalFragment :
    BaseFragment<FragmentMedicalApprovalBinding, MedicalApprovalViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: MedicalApprovalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}