package com.gsgroup.hrapp.ui.activity.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseActivity
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.databinding.ActivityDetailsBinding
import com.gsgroup.hrapp.ui.fragment.home.HomeFragmentDirections
import com.gsgroup.hrapp.util.navigateSafe
import com.gsgroup.hrapp.util.observe
import timber.log.Timber

class DetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>(),
    NavController.OnDestinationChangedListener {

    override val mViewModel: DetailsViewModel by viewModels()
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavController()
        mViewModel.apply {
            showProgressBar = showProgress
            observe(mutableLiveData) {
                when (it) {
                    Codes.BACK_BUTTON_PRESSED -> onBackPressed()
                }
            }
        }
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        val destination = intent.getIntExtra(ConstString.DESTINATION_NAME, R.id.splashFragment)
        val navGraph = navController?.navInflater?.inflate(R.navigation.details_nav_graph)
        navGraph?.startDestination = destination
        navController?.setGraph(navGraph!!, intent.extras)
        navController?.addOnDestinationChangedListener(this)
    }

    fun changeTitle(title: String?) {
        title?.let {
            mViewModel.obsTitle.set(title)
        } ?: Timber.e("title is null")
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        destination.id.let { id ->
            mViewModel.setScreenPermissions(id)
        }
    }

}
