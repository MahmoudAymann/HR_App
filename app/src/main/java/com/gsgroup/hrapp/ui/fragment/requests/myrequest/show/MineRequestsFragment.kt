package com.gsgroup.hrapp.ui.fragment.requests.myrequest.show

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.databinding.FragmentMineRequestsBinding

class MineRequestsFragment :
    BaseFragment<FragmentMineRequestsBinding, MineRequestsViewModel>() {
    val args: MineRequestsFragmentArgs by navArgs()

    override fun pageTitle(): String =
        getString(if (args.isMine) R.string.my_requests else R.string.requests_sent_to_me)

    override val mViewModel: MineRequestsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}