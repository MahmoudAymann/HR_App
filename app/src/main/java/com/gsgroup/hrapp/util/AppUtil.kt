package com.gsgroup.hrapp.util

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import androidx.annotation.FontRes
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.app.BaseApplication
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by MahmoudAyman on 8/25/2020.
 **/

object AppUtil {

    fun getNumberOfDays(year: Int, month: Int) : Int{
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        return calendar.getActualMaximum(Calendar.DATE)
    }

    fun isOldDevice():Boolean{
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
    }

    fun Context.getFont(@FontRes fontRes:Int) : Typeface? {
        return ResourcesCompat.getFont(this, fontRes)
    }

    private fun getMinDate(date: String): Calendar {
        val day = date.split("-").toTypedArray()
        val c: Calendar = getCurrentCalInstance()
        c.set(day[0].toInt(), day[1].toInt()-1, day[2].toInt())
        return c
    }

    fun getDaysInMonth(c: Calendar, year: Int, month: Int): Int {
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month
        return c.getActualMaximum(Calendar.DATE)
    }

    private fun getCurrentCalInstance(): Calendar {
        val mCalendar = Calendar.getInstance()
        return mCalendar
    }

    fun <T> setSpinner(spinner:Spinner, list: ArrayList<T> = ArrayList(), liveData: MutableLiveData<T?>) {
        val adapter = ArrayAdapter(
            spinner.context,
            android.R.layout.simple_spinner_dropdown_item,
            list
        )
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                liveData.value = parent.selectedItem as T
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    fun openAppInStore(context: FragmentActivity) {
        val uri = Uri.parse("market://details?id=" + context.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            context.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.packageName)
                )
            )
        }
    }
}