package com.gsgroup.hrapp.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.gsgroup.hrapp.BR
import com.gsgroup.hrapp.ui.activity.MainActivity
import com.gsgroup.hrapp.ui.activity.details.DetailsActivity
import com.gsgroup.hrapp.util.*
import timber.log.Timber

/**
 * Created by MahmoudAyman on 6/17/2020.
 **/

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> :
    Fragment(){

    abstract fun pageTitle(): String?

    protected abstract val mViewModel: VM

    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, mViewModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        changeMainTitle(pageTitle())
    }

    fun closeFragment() {
        showKeyboard(false)
        activity?.onBackPressed()
    }

    fun showDetailsActivity(@IdRes destinationId: Int, intent: Intent = Intent()) {
        activity?.showActivityWithDestination<DetailsActivity>(destinationId, intent)
    }

     fun showMainActivity(intent: Intent = Intent()) {
         activity?.showActivity<MainActivity>(intent)
    }

    fun showProgress(show: Boolean = true) {
        castToActivity<BaseActivity<*, *>> {
            it?.baseShowProgress?.set(show)
        }
    }


    private fun changeMainTitle(title: String?) {
        castToActivity<MainActivity> {
            it?.changeTitle(title)
        } ?: castToActivity<DetailsActivity> {
            it?.changeTitle(title)
        } ?: Timber.e("cannot find activity")
    }

    inline fun <reified T : BaseFragment<*,*>> replaceFragment(
        bundle: Bundle? = null
    ) {
        activity?.replaceFragment<T>(bundle)
    }

    fun showBottomBar(show: Boolean = true) {
        castToActivity<MainActivity> {
            it?.showBottomBar(show)
        }
    }


    override fun onPause() {
        super.onPause()
        showProgress(false)
    }

}
