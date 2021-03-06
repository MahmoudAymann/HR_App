package com.gsgroup.hrapp.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.gsgroup.hrapp.BR
import com.gsgroup.hrapp.util.LocalUtil
import com.gsgroup.hrapp.util.bindView


/**
 * Created by MahmoudAyman on 7/17/2020.
 **/

abstract class BaseActivity <B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    val baseShowProgress = ObservableBoolean()
    protected abstract val mViewModel: VM
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        LocalUtil.changeLanguage(this)
        super.onCreate(savedInstanceState)
        LocalUtil.changeLanguage(this)
        binding = bindView()
        binding.setVariable(BR.viewModel, mViewModel)
    }


}
