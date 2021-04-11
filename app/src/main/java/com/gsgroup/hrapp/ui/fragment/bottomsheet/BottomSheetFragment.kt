package com.gsgroup.hrapp.ui.fragment.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.databinding.FragmentBottomSheetBinding
import com.gsgroup.hrapp.util.observe


class BottomSheetFragment : BottomSheetDialogFragment() {

    val mViewModel: BottomSheetViewModel by viewModels()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    lateinit var binding: FragmentBottomSheetBinding

    private val args: BottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = mViewModel.apply {
            gotData(args)
            observe(mutableLiveData) {
                when (it) {
                    Codes.BACK_BUTTON_PRESSED -> dismiss()
                }
            }
        }
    }

    private val callBack = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            mViewModel.handleStates(newState)
        }
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            mViewModel.obsShowHeader.set(false)
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { view ->
                bottomSheetBehavior = BottomSheetBehavior.from(view)
                setupFullHeight(view)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                bottomSheetBehavior.addBottomSheetCallback(callBack)
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }
}