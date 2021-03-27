package com.gsgroup.hrapp.ui.fragment.covid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentCovidBinding
import com.gsgroup.hrapp.util.observe

class CovidFragment : BaseFragment<FragmentCovidBinding, CovidViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: CovidViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData){
                when(it){

                }
            }

            observe(resultLiveData){

            }
        }
    }




}