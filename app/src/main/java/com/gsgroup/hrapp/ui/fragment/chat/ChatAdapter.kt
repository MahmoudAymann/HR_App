package com.gsgroup.hrapp.ui.fragment.chat

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class ChatAdapter(itemCallback: (ChatItem) -> Unit) : BaseAdapter<ChatItem>(itemCallback) {
    override fun layoutRes(): Int = R.layout.item_chat_view

    override fun onViewHolderCreated(viewHolder: BaseViewHolder<ChatItem>) {

    }
}