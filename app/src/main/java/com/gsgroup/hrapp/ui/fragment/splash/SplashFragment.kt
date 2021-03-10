package com.gsgroup.hrapp.ui.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    override fun pageTitle(): String? = null


//        viewModel.apply {
//            binding.imgAman.initAnimation(R.anim.rtl_en, 1) {
//                obsHideChild.set(true)
//            }
//            binding.imgGoPlus.initAnimation(R.anim.ltr_en, 1) {
//                binding.imgLogos.initAnimation(R.anim.slide_from_top_to_bottom, 1) {
////                    replaceFragment<LoginFragment>()
//                }
//            }
//        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}