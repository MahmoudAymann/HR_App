package com.gsgroup.hrapp.ui.fragment.bottomsheet

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.databinding.adapters.TextViewBindingAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.ui.fragment.login.AreasItem
import timber.log.Timber

class BottomSheetViewModel : BaseViewModel() {

    val adapter = SearchAdapter(::onItemClick)
    val obsShowHeader = ObservableBoolean()

    private fun onItemClick(searchItem: SearchItemInterface) {
        setValue(searchItem)
    }

    fun onCloseClick(){
        setValue(Codes.BACK_BUTTON_PRESSED)
    }

    fun handleStates(newState: Int) {
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                obsShowHeader.set(true)
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {

            }
            BottomSheetBehavior.STATE_HALF_EXPANDED->{
                obsShowHeader.set(false)
            }
            BottomSheetBehavior.STATE_HIDDEN -> {
                setValue(Codes.BACK_BUTTON_PRESSED)
            }
        }
    }

    fun onTextChange(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, _, _, _ ->
            adapter.filter.filter(s)
        }
    }

    fun gotData(args: BottomSheetFragmentArgs) {
        args.data?.let {
            adapter.setList(it.toList())
        }  ?: Timber.e("list is null")
    }

}