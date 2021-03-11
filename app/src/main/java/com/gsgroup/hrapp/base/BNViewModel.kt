package com.gsgroup.hrapp.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import timber.log.Timber

class BNViewModel: ViewModel() {
    val obsText = ObservableField<String>("ggggggggggggggggggggg")
    init {
        Timber.e("done ya baby")
    }
}