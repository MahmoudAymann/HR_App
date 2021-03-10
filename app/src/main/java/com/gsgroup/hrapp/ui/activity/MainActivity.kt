package com.gsgroup.hrapp.ui.activity

import android.os.Bundle
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseActivity
import com.gsgroup.hrapp.databinding.ActivityMainBinding
import com.gsgroup.hrapp.ui.fragment.login.LoginFragment
import com.gsgroup.hrapp.ui.fragment.splash.SplashFragment
import com.gsgroup.hrapp.util.*
import com.tenclouds.fluidbottomnavigation.listener.OnTabSelectedListener
import timber.log.Timber

class MainActivity : BaseActivity(), OnTabSelectedListener {

    private val binding by bindView(ActivityMainBinding::inflate)
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = bindViewModel(binding) {

        }
        replaceFragment<LoginFragment>()
    }

    override fun onBackPressed() {
        if (findFragmentById(R.id.fragment_container) is SplashFragment) {
            showExitDialog()
        } else
            super.onBackPressed()
    }

    fun changeTitle(title: String?) {
        title?.let {
//            viewModel.obsTitle.set(title)
        } ?: Timber.e("title is null")
    }

    fun showBottomBar(show: Boolean = true) {
//        viewModel.obsShowBottomBar.set(show)
    }

    //
    override fun onTabSelected(position: Int) {
        when (position) {

        }
    }
}
//
//    override fun layoutRes(): Int = R.layout.activity_main
