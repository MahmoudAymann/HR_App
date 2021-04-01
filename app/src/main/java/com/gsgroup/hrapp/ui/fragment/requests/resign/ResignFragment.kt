package com.gsgroup.hrapp.ui.fragment.requests.resign

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentResignBinding

class ResignFragment : BaseFragment<FragmentResignBinding, ResignViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: ResignViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}