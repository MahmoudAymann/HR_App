package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class LogsAdapter(itemClick: (LogsItem) -> Unit) : BaseAdapter<LogsItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_notification_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<LogsItem>) {

    }
}