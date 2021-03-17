package com.gsgroup.hrapp.ui.fragment.chat

import com.gsgroup.hrapp.base.BaseViewModel
import okhttp3.Interceptor

class ChatViewModel : BaseViewModel() {

    val adapter = ChatAdapter(::onItemClick)

    private fun onItemClick(chatItem: ChatItem) {

    }

    init {
        adapter.setList(ChatItem.getDummyList())
    }

}