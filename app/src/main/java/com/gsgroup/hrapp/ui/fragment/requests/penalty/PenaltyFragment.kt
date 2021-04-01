package com.gsgroup.hrapp.ui.fragment.requests.penalty

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentPenaltyBinding

class PenaltyFragment : BaseFragment<FragmentPenaltyBinding, PenaltyViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: PenaltyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}