package com.gsgroup.hrapp.ui.fragment.myteam

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder

class MyTeamAdapter(itemClick: (MyTeamItem) -> Unit) : BaseAdapter<MyTeamItem>(itemClick) {

    override fun layoutRes(): Int = R.layout.item_my_team_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<MyTeamItem>) {

    }
}