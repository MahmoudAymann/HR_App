package com.gsgroup.hrapp.ui.fragment.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentNewsBinding

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {
    override fun pageTitle(): String = getString(
        R.string.news)
    override val mViewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}