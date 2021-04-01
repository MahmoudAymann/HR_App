package com.gsgroup.hrapp.ui.fragment.requests.borrow

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.base.BaseViewModel
import com.gsgroup.hrapp.constants.Codes
import com.gsgroup.hrapp.util.removeSpaces

/**
 * Created by MahmoudAyman on 8/23/2020.
 **/
class BorrowViewModel(app:Application) : AndroidBaseViewModel(app) {
    val obsIsStaff = ObservableInt(-1)
    val obsIsAnnual = ObservableInt(-1)
    val obsShowWorkingDays = ObservableBoolean()
    val obsSalary = ObservableField<String>()
    val obsCompanyAmount = ObservableField<String>()
    val obsWorkingDays = ObservableField<String>()
    val obsWorkingPeriodLess = ObservableInt(-1)
    val obsWorkPeriodLessText = ObservableField<String>()
    val obsWorkPeriodMoreText = ObservableField<String>()

    fun onSubmitClick() {

    }

    fun onStaffClick(isStaff: Boolean) {
        if (isStaff) {
            obsIsStaff.set(1)
            obsWorkPeriodLessText.set(app.getString(R.string.less_than_3))
            obsWorkPeriodMoreText.set(app.getString(R.string.more_than_3))
        } else { //is representative
            obsIsStaff.set(0)
            obsWorkPeriodLessText.set(app.getString(R.string.less_than_6))
            obsWorkPeriodMoreText.set(app.getString(R.string.more_than_6))
        }
        showWorkingDaysField()
    }

    private fun showWorkingDaysField() {
        if (obsIsStaff.get() == 0) { // is representative
            if (obsWorkingPeriodLess.get() == 1) {
                if (obsIsAnnual.get() == 0)
                    obsShowWorkingDays.set(true)
                else {
                    obsWorkingDays.set(null)
                    obsShowWorkingDays.set(false)
                }
            } else if (obsWorkingPeriodLess.get() == 0) { //is more
                if (obsIsAnnual.get() == -1 || obsIsAnnual.get() == 1)
                    obsShowWorkingDays.set(false)
                else {
                    obsShowWorkingDays.set(true)
                }
            }
        } else {
            obsWorkingDays.set(null)
            obsShowWorkingDays.set(false)
        }
        calculateAmount(obsSalary.get())
    }

    fun onMonthlyClick(isAnnual: Boolean) {
        obsIsAnnual.set(if (isAnnual) 1 else 0)
        showWorkingDaysField()
    }

    fun onWorkPeriod1Click(isWorkingDay1: Boolean) {
        obsWorkingPeriodLess.set(if (isWorkingDay1) 1 else 0)
        showWorkingDaysField()
    }

    fun getWorkingDaysTextChanged(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAmount(obsSalary.get())
            }
        }
    }

    fun getSalaryTextChanged(): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAmount(s)
            }
        }
    }

    private fun calculateAmount(s: CharSequence?) {
        s?.let {
            if (it.isEmpty()) obsCompanyAmount.set(null)
            else {
                val salary = it.removeSpaces().toString().toInt()
                when (obsIsStaff.get()) {
                    1 -> {// is staff
                        obsCompanyAmount.set(
                            calcStaff(
                                salary,
                                obsWorkingPeriodLess.get() == 1,
                                obsIsAnnual.get() == 1
                            )
                        )
                    }
                    else -> { //is representative
                        obsCompanyAmount.set(
                            calcRepresentative(
                                salary,
                                obsWorkingPeriodLess.get() == 1,
                                obsIsAnnual.get() == 1
                            )
                        )
                    }
                }
            }
        }
    }

    private fun calcStaff(
        salary: Int,
        isWorkingPeriodLess: Boolean,
        isAnnual: Boolean
    ): String? {
        return if (isWorkingPeriodLess) { //is less
            if (!isAnnual) // monthly
                "${(salary * (25f / 100f)).toInt()}"
            else // annual
                "${salary * 0}"
        } else { // is more
            if (!isAnnual) { // monthly
                "${(salary * (70f / 100f)).toInt()}"
            } else { // annual
                "$salary"
            }
        }
    }

    private fun calcRepresentative(
        salary: Int,
        isWorkingPeriodLess: Boolean,
        isAnnual: Boolean
    ): String? {
        return if (isWorkingPeriodLess) { //is less
            if (!isAnnual) {  // monthly
                getWorkingDays()?.let {
                    "${((salary / 30f) * it.toFloat()).toInt()}"
                }
            } else // annual
                "${salary * 0}"
        } else { // is more
            if (!isAnnual)  // monthly
                getWorkingDays()?.let {
                    "${((salary / 30f) * it.toFloat()).toInt()}"
                }
            else  // annual
                "$salary"
        }
    }


    private fun getWorkingDays(): String? = if (obsWorkingDays.get().isNullOrEmpty()) {
        obsCompanyAmount.set(null)
        null
    } else obsWorkingDays.get()



}
