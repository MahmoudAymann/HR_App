package com.gsgroup.hrapp.ui.fragment.covid

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentCovidBinding
import com.gsgroup.hrapp.util.asUri
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.openInBrowser
import com.gsgroup.hrapp.util.showWarningDialog

class CovidFragment : BaseFragment<FragmentCovidBinding, CovidViewModel>() {
    override fun pageTitle(): String = getString(R.string.covid_19)
    override val mViewModel: CovidViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {
            observe(mutableLiveData){
                when(it){
                    Codes.NEWS_SCREEN-> COVID_LINK.asUri().openInBrowser(requireActivity())
                    Codes.INFECT_SCREEN->requireActivity().showWarningDialog(getString(R.string.still_working_on_it))
                }
            }

            observe(resultLiveData){

            }
        }
    }



    companion object{
        const val COVID_LINK = "https://www.care.gov.eg/EgyptCare/Index.aspx"
    }


}