package com.gsgroup.hrapp.ui.fragment.requests.hire.replacement

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentReplacementEmployeeBinding

class ReplacementEmployeeFragment :
    BaseFragment<FragmentReplacementEmployeeBinding, ReplacementEmployeeViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: ReplacementEmployeeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}