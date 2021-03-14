package com.gsgroup.hrapp.ui.fragment.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentNotificfationBinding

class NotificationFragment : BaseFragment<FragmentNotificfationBinding, NotificationViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: NotificationViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}