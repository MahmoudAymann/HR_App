package com.gsgroup.hrapp.ui.fragment.myteam

import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseAdapter
import com.gsgroup.hrapp.base.BaseViewHolder
import com.gsgroup.hrapp.databinding.ItemMyTeamViewBinding

class MyTeamAdapter(
    val btnChangePassword: (MyTeamItem) -> Unit,
    val btnSignInOut: (MyTeamItem) -> Unit
) : BaseAdapter<MyTeamItem>() {

    override fun layoutRes(): Int = R.layout.item_my_team_view
    override fun onViewHolderCreated(viewHolder: BaseViewHolder<MyTeamItem>) {
        (viewHolder.binding as ItemMyTeamViewBinding).fabChangePassword.setOnClickListener {
            btnChangePassword(getItem(viewHolder.adapterPosition))
        }
        viewHolder.binding.fabLoginLogs.setOnClickListener {
            btnSignInOut(getItem(viewHolder.adapterPosition))
        }
    }
}