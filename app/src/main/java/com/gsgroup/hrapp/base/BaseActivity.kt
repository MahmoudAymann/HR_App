package com.gsgroup.hrapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.gsgroup.hrapp.util.LocalUtil
import com.gsgroup.hrapp.util.bindViewModel

/**
 * Created by MahmoudAyman on 7/17/2020.
 **/

abstract class BaseActivity : AppCompatActivity() {

    val showProgress = ObservableBoolean()
    override fun onCreate(savedInstanceState: Bundle?) {
        LocalUtil.changeLanguage(this)
        super.onCreate(savedInstanceState)
        LocalUtil.changeLanguage(this)
    }


}
