package com.gsgroup.hrapp.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.gsgroup.hrapp.BR
import com.gsgroup.hrapp.util.getTClass

/**
 * Created by MahmoudAyman on 7/17/2020.
 **/

abstract class TestActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    protected abstract val mViewModel: VM
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding()
        binding.setVariable(BR.viewModel, mViewModel)
    }


    private fun <B : ViewDataBinding> Activity.getBinding(): B {
        val inflateMethod = getTClass<B>().getMethod("inflate", LayoutInflater::class.java)
        val invokeLayout = inflateMethod.invoke(null, this.layoutInflater) as B
        this.setContentView(invokeLayout.root)
        return invokeLayout
    }


}
