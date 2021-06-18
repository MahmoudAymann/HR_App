package com.gsgroup.hrapp.ui.fragment.company_polices

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.databinding.FragmentCompanyPoliciesBinding
import com.gsgroup.hrapp.ui.fragment.news.NewsItem
import com.gsgroup.hrapp.ui.fragment.news.NewsFragmentDirections
import com.gsgroup.hrapp.util.*

class CompanyPoliciesFragment :
    BaseFragment<FragmentCompanyPoliciesBinding, CompanyPoliciesViewModel>() {
    override fun pageTitle(): String = getString(R.string.company_policy)
    override val mViewModel: CompanyPoliciesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    is CompanyPolicyItem -> {
                        if (it.file != null)
                            it.file.asUri().openInBrowser(requireActivity())
//                            navigateSafe(
//                                CompanyPoliciesFragmentDirections.actionCompanyPoliciesFragmentToWebViewFragment(
//                                    it.title,
//                                    it.file
//                                )
//                            )
                    }
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