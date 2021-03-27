package com.gsgroup.hrapp.ui.fragment.requests.printinsurance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentPrintInsuranceBinding

class PrintInsuranceFragment : BaseFragment<FragmentPrintInsuranceBinding, PrintInsuranceViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: PrintInsuranceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}