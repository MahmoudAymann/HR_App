package com.gsgroup.hrapp.ui.fragment.requests.complain

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentComplainBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog

class ComplainFragment : BaseFragment<FragmentComplainBinding, ComplainViewModel>() {
    override fun pageTitle(): String = getString(R.string.complain)
    override val mViewModel: ComplainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {

                }
            }
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                    }
                    Status.MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    Status.LOADING -> showProgress()
                }
            }
        }
    }
}