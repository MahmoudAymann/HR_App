package com.gsgroup.hrapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.gsgroup.hrapp.ui.activity.MainActivity
import com.gsgroup.hrapp.util.castToActivity
import com.gsgroup.hrapp.util.replaceFragment
import com.gsgroup.hrapp.util.showKeyboard

/**
 * Created by MahmoudAyman on 6/17/2020.
 **/

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    abstract fun pageTitle(): String?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        changeMainTitle(pageTitle())
    }

    fun closeFragment() {
        showKeyboard(false)
        activity?.onBackPressed()
    }

    fun showProgress(show: Boolean = true) {
        castToActivity<BaseActivity> {
            it?.showProgress?.set(show)
        }
    }

    fun changeMainTitle(title: String?) {
        castToActivity<MainActivity> {
//            it?.changeTitle(title)
        }
    }

    inline fun <reified T : BaseFragment> replaceFragment(
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
