package com.gsgroup.hrapp.ui.fragment.hr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.SearchItem
import com.gsgroup.hrapp.databinding.FragmentHrBinding
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe

class HRFragment : BaseFragment<FragmentHrBinding, HRViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: HRViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.NEWS_SCREEN -> navigateSafe(
                        HRFragmentDirections.actionHRFragmentToBottomSheetFragment(SearchItem.getDummyData())
                    )
                }
            }

        }
    }
}