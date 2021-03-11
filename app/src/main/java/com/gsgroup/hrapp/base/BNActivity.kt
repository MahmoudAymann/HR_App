package com.gsgroup.hrapp.base

import android.widget.Toast
import androidx.activity.viewModels
import com.gsgroup.hrapp.databinding.BnActivtyBinding
import timber.log.Timber

class BNActivity : TestActivity<BnActivtyBinding, BNViewModel>() {

    override val mViewModel: BNViewModel by viewModels()



}