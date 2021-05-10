package com.gsgroup.hrapp.ui.fragment.requests.complain

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.fxn.pix.Pix
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentComplainBinding
import com.gsgroup.hrapp.util.Status
import com.gsgroup.hrapp.util.observe
import com.gsgroup.hrapp.util.showErrorDialog
import com.gsgroup.hrapp.util.showSuccessfulDialog

class ComplainFragment : BaseFragment<FragmentComplainBinding, ComplainViewModel>() {
    override fun pageTitle(): String = getString(R.string.complain)
    override val mViewModel: ComplainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.apply {

            observe(mutableLiveData) {
                when (it) {
                    Codes.PICK_IMAGE_CODE -> pickImage(it as Int)
                }
            }

            observe(resultLiveData) {
                when (it?.status) {
                    Status.SUCCESS -> {
                        showProgress(false)
                        activity?.showSuccessfulDialog(it.message) {
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Codes.PICK_IMAGE_CODE) {
                data?.let {
                    val returnValue = it.getStringArrayListExtra(Pix.IMAGE_RESULTS)
                    returnValue?.let { array ->
                        mViewModel.gotImage(array[0])
                        binding.bigImage.setImageURI(array[0].toUri())
                    }
                }
            }
        }
    }


}