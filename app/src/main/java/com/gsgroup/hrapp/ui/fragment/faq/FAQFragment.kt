package com.gsgroup.hrapp.ui.fragment.faq

import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentFaqBinding

class FAQFragment : BaseFragment<FragmentFaqBinding, FAQViewModel>() {
    override fun pageTitle(): String = getString(R.string.faq)
    override val mViewModel: FAQViewModel by viewModels()
}