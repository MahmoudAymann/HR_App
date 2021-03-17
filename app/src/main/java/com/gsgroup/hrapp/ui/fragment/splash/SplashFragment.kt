package com.gsgroup.hrapp.ui.fragment.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentSplashBinding
import com.gsgroup.hrapp.ui.fragment.login.LoginFragment
import com.gsgroup.hrapp.util.initAnimation
import com.gsgroup.hrapp.util.navigateSafe

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun pageTitle(): String? = null

    override val mViewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            binding.imgAman.initAnimation(R.anim.rtl_en, 1) {
                obsHideChild.set(true)
            }
            binding.imgGoPlus.initAnimation(R.anim.ltr_en, 1) {
                binding.imgLogos.initAnimation(R.anim.slide_from_top_to_bottom, 1) {
                    navigateSafe(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                }
            }
        }
    }


}