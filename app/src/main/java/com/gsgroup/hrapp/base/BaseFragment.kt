package com.gsgroup.hrapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.gsgroup.hrapp.BR
import com.gsgroup.hrapp.ui.activity.MainActivity
import com.gsgroup.hrapp.util.bindView
import com.gsgroup.hrapp.util.castToActivity
import com.gsgroup.hrapp.util.replaceFragment
import com.gsgroup.hrapp.util.showKeyboard

/**
 * Created by MahmoudAyman on 6/17/2020.
 **/

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {

    abstract fun pageTitle(): String?

    protected abstract val mViewModel: VM
    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindView()
        binding.setVariable(BR.viewModel, mViewModel)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        changeMainTitle(pageTitle())
    }

    fun closeFragment() {
        showKeyboard(false)
        activity?.onBackPressed()
    }

    fun showProgress(show: Boolean = true) {
        castToActivity<BaseActivity<*,*>> {
            it?.showProgress?.set(show)
        }
    }

    fun changeMainTitle(title: String?) {
        castToActivity<MainActivity> {
//            it?.changeTitle(title)
        }
    }

    inline fun <reified T : BaseFragment<*,*>> replaceFragment(
        bundle: Bundle? = null
    ) {
        activity?.replaceFragment<T>(bundle)
    }

    fun showBottomBar(show: Boolean = true) {
        castToActivity<MainActivity> {
//            it?.showBottomBar(show)
        }
    }


    override fun onPause() {
        super.onPause()
        showProgress(false)
    }

}
