package com.gsgroup.hrapp.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseActivity
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.databinding.ActivityMainBinding
import com.gsgroup.hrapp.ui.fragment.home.HomeFragmentDirections
import com.gsgroup.hrapp.util.AppUtil.getFont
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefLanguage
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showExitDialog
import com.gsgroup.hrapp.util.showLogoutDialog
import com.tenclouds.fluidbottomnavigation.listener.OnTabSelectedListener
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), OnTabSelectedListener,
    NavController.OnDestinationChangedListener {

    override val mViewModel: MainViewModel by viewModels()
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        navController?.addOnDestinationChangedListener(this)
        binding.bottomNavBar.onTabSelectedListener = this
        binding.bottomNavBar.textFont =
            if (getPrefLanguage() == ConstString.LANG_AR) getFont(R.font.pnu_medium_arabic)!! else getFont(
                R.font.segoe_reg
            )!!
        mViewModel.apply {
            observe(mutableLiveData) {
                when (it) {
                    Codes.BACK_BUTTON_PRESSED -> onBackPressed()
                    Codes.COVID_SCREEN->""
                    Codes.SALARY_SCREEN -> navigateSafe(HomeFragmentDirections.actionHomeFragmentToSalaryFragment())
                    Codes.LOG_OUT -> showLogoutDialog {
                        navigateSafe(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
                    }
                }
            }
        }
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
            0 -> navigateSafe(R.id.homeFragment)
            1 -> navigateSafe(R.id.chatFragment)
            2 -> navigateSafe(R.id.notificationFragment)
            3 -> navigateSafe(R.id.faqFragment)
            4 -> navigateSafe(R.id.settingsFragment)
        }
    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        destination.id.let { id ->
            mViewModel.setScreenPermissions(id)
            when (id) {
                R.id.homeFragment -> selectTab(0)
                R.id.chatFragment -> selectTab(1)
                R.id.notificationFragment -> selectTab(2)
                R.id.faqFragment -> selectTab(3)
                R.id.settingsFragment -> selectTab(4)
            }
        }
    }

    private fun selectTab(index: Int) {
        if (index >= 0 && index < binding.bottomNavBar.items.size)
            binding.bottomNavBar.selectTab(index)
    }

    override fun onBackPressed() {
        when (navController?.currentDestination?.id) {
            R.id.loginFragment -> {
                showExitDialog()
            }
            R.id.splashFragment -> {
            }
            R.id.homeFragment -> showExitDialog()
            else -> {
                navController?.navigateUp()
            }
        }
    }

}
