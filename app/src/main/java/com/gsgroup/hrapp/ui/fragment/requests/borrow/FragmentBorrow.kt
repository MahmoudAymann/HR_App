package com.gsgroup.hrapp.ui.fragment.requests.borrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.app.BaseApplication
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentBorrowBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog
import com.gsgroup.hrapp.util.showSuccessfulDialog

/**
 * Created by MahmoudAyman on 8/23/2020.
 **/
class FragmentBorrow : BaseFragment<FragmentBorrowBinding, BorrowViewModel>() {


    override fun pageTitle(): String = getString(R.string.borrow_request)

    override val mViewModel: BorrowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.apply {
            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        activity?.showSuccessfulDialog(it.message){
                            closeFragment()
                        }
                    }
                    Status.MESSAGE -> {
                        showProgress(false)
                        activity?.showErrorDialog(it.message)
                    }
                    Status.LOADING -> showProgress()
                }
            }
        }
    }

}