package com.gsgroup.hrapp.ui.fragment.news.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding, NewsDetailsViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: NewsDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}