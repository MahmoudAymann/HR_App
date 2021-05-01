package com.gsgroup.hrapp.ui.fragment.chat

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.fragment.app.viewModels
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentChatBinding

class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {
    override fun pageTitle(): String = ""
    override val mViewModel: ChatViewModel by viewModels()

}