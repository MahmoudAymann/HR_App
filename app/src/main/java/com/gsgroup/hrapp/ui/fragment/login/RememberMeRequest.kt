package com.gsgroup.hrapp.ui.fragment.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RememberMeRequest(
    var natId: String? = null,
    var password: String? = null
) : Parcelable