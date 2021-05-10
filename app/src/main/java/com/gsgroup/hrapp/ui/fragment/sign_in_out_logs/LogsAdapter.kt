package com.gsgroup.hrapp.ui.fragment.sign_in_out_logs

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder
import com.gsgroup.hrapp.databinding.ItemLogsViewBinding

class LogsAdapter(val itemClick: (LogsItem) -> Unit) : BaseAdapter<LogsItem>() {

    override fun layoutRes(): Int = R.layout.item_logs_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<LogsItem>) {
        (viewHolder.binding as ItemLogsViewBinding).fabDay.setOnClickListener {
            itemClick(getItem(viewHolder.adapterPosition))
        }
    }
}