package com.gsgroup.hrapp.ui.fragment.notification

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class NotificationAdapter(itemClick: (NotificationItem) -> Unit) : BaseAdapter<NotificationItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_notification_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<NotificationItem>) {

    }
}