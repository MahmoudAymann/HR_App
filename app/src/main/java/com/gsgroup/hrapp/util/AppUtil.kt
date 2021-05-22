package com.gsgroup.hrapp.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefFirebaseToken
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by MahmoudAyman on 8/25/2020.
 **/

object AppUtil {

    @SuppressLint("MissingPermission", "HardwareIds")
    fun getDeviceSerial(applicationContext: Context): String {

        var serialNumber: String

        try {
            val c = Class.forName("android.os.SystemProperties")
            val get = c.getMethod("get", String::class.java)

            serialNumber = get.invoke(c, "gsm.sn1") as String

            when (serialNumber) {
                "" -> serialNumber = get.invoke(c, "ril.serialnumber") as String
            }

            when (serialNumber) {
                "" -> serialNumber = get.invoke(c, "ro.serialno") as String
            }

            when (serialNumber) {
                "" -> serialNumber = get.invoke(c, "sys.serialnumber") as String
            }

            when (serialNumber) {
                "" -> serialNumber = Build.SERIAL
            }

            when (serialNumber) {
                "" -> serialNumber = applicationContext.getPrefFirebaseToken()
            }

        } catch (e: Exception) {
            Timber.e(e)
            serialNumber = applicationContext.getPrefFirebaseToken()
        }

        if (serialNumber == "unknown") {
            try {
                val c = Class.forName("android.os.SystemProperties")
                val get = c.getMethod(
                    "get",
                    String::class.java,
                    String::class.java
                )
                serialNumber = get.invoke(c, "ril.serialnumber", "unknown") as String
            } catch (ignored: Exception) {
                Timber.d("BuildSerialException getDeviceSerial: $ignored")
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && serialNumber == "unknown") {
            if (PermissionUtil.isGranted(applicationContext, Manifest.permission.READ_PHONE_STATE)) {
                serialNumber = Settings.Secure.getString(
                    applicationContext.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && serialNumber == "unknown") {
            if (PermissionUtil.isGranted(applicationContext, Manifest.permission.READ_PHONE_STATE)
                && PermissionUtil.isGranted(
                    applicationContext,
                    Manifest.permission.READ_PRECISE_PHONE_STATE
                )
            ) {
                serialNumber = Build.getSerial()
            }
        }
        if (serialNumber == "unknown"){
            serialNumber =  applicationContext.getPrefFirebaseToken()
        }
        return serialNumber
    }

    fun Context.getDrawableForLiteVersions(@DrawableRes drawableRes: Int): Drawable? {
        return if (isOldDevice())
            VectorDrawableCompat.create(resources, drawableRes, theme)
        else
            ContextCompat.getDrawable(this, drawableRes)
    }

    fun Context.callPhoneNumber(phone: String?) {
        phone?.let {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${phone}")
            startActivity(intent)
        }
    }

    fun Context.openMailIntent(mail: String?) {
        mail?.let {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:$mail")
            startActivity(Intent.createChooser(emailIntent, "send mail"))
        }
    }

    fun getNumberOfDays(year: Int, month: Int): Int {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1)
        return calendar.getActualMaximum(Calendar.DATE)
    }

    fun isOldDevice(): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
    }

    fun Context.getFont(@FontRes fontRes: Int) : Typeface? {
        return ResourcesCompat.getFont(this, fontRes)
    }

    private fun getMinDate(date: String): Calendar {
        val day = date.split("-").toTypedArray()
        val c: Calendar = getCurrentCalInstance()
        c.set(day[0].toInt(), day[1].toInt() - 1, day[2].toInt())
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

    fun <T> setSpinner(
        spinner: Spinner,
        list: ArrayList<T> = ArrayList(),
        liveData: MutableLiveData<T?>
    ) {
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

    @Suppress("DEPRECATION")
     fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    fun openTimePicker(context: Context, callBack: (Calendar) -> Unit) {
        val mCurrentTime = Calendar.getInstance()
        val hour = mCurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mCurrentTime[Calendar.MINUTE]
        val mTimePicker = TimePickerDialog(
            context,
            { _, selectedHrOfDay, selectedMin ->
                val mC = Calendar.getInstance()
                mC[Calendar.HOUR_OF_DAY] = selectedHrOfDay
                mC[Calendar.MINUTE] = selectedMin
                callBack(mC)
            },
            hour,
            minute,
            false
        ) //Yes 24 hour time
        mTimePicker.show()
        setPickerButtonsStyle(context, mTimePicker)
    }


    fun openDatePicker(
        context: Context,
        startDate: String? = null,
        resultCallBack: (String) -> Unit
    ): DatePickerDialog {
        val mCalendar = getCurrentCalInstance()
        val year = mCalendar[Calendar.YEAR]
        val month = mCalendar[Calendar.MONTH]
        val day = mCalendar[Calendar.DAY_OF_MONTH]
        val mDatePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker?, year1: Int, month1: Int, dayOfMonth: Int ->
                val format =
                    SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val calendar = Calendar.getInstance()
                calendar[year1, month1] = dayOfMonth
                val strDate = format.format(calendar.time)
                resultCallBack(strDate)
            },
            year, month, day
        )
        startDate?.let {
            val format =
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val calendar = Calendar.getInstance()
            val date = format.parse(it)
            date?.let {
                calendar.time = date
                calendar.add(Calendar.DAY_OF_MONTH, 1)
                mDatePickerDialog.datePicker.minDate = calendar.timeInMillis - 10000
            } ?: Timber.e("format date isn't correct")
        }
        mDatePickerDialog.show()
        setPickerButtonsStyle(context, mDatePickerDialog)
        return mDatePickerDialog
    }

    private fun setPickerButtonsStyle(context: Context, type: AlertDialog) {
        type.getButton(DialogInterface.BUTTON_POSITIVE)
            .setTextColor(context.getColorFromRes(R.color.white))
        type.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(
            context.getColorFromRes(
                R.color.colorPrimary
            )
        )
        type.getButton(DialogInterface.BUTTON_NEGATIVE)
            .setTextColor(Color.BLACK)
    }

}