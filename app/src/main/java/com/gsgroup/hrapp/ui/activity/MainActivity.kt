package com.gsgroup.hrapp.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseActivity
import com.gsgroup.hrapp.databinding.ActivityMainBinding
import com.gsgroup.hrapp.ui.fragment.login.LoginFragment
import com.gsgroup.hrapp.ui.fragment.splash.SplashFragment
import com.gsgroup.hrapp.util.*
import com.tenclouds.fluidbottomnavigation.listener.OnTabSelectedListener
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), OnTabSelectedListener {

    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment<SplashFragment>()
    }

    override fun onBackPressed() {
        if (findFragmentById(R.id.fragment_container) is SplashFragment) {
            showExitDialog()
        } else
            super.onBackPressed()
    }

    fun changeTitle(title: String?) {
        title?.let {
            mViewModel.obsTitle.set(title)
        } ?: Timber.e("title is null")
    }

    fun showBottomBar(show: Boolean = true) {
        mViewModel.obsShowBottomBar.set(show)
    }

    override fun onTabSelected(position: Int) {
        when (position) {

        }
    }
}
//
//    override fun layoutRes(): Int = R.layout.activity_main
