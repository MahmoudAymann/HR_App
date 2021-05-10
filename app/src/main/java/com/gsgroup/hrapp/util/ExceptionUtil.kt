package com.gsgroup.hrapp.util

import android.accounts.NetworkErrorException
import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.data.model.ErrorResponse
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.ContentHandler

object ExceptionUtil {


    val USER_ERROR :Int = 422

    fun Exception.getErrorFromException(app: Context): String? {
       return when(this){
            is HttpException->{
                val body = response()?.errorBody()
                val adapter = Gson().getAdapter(ErrorResponse::class.java)
                try {
                    val res: ErrorResponse = adapter.fromJson(body?.string())
                    return when(res.status){
                        USER_ERROR->res.errors?.get(0)
                        else -> res.message
                    }
                } catch (e: IOException) {
                    Timber.e(e)
                    app.getString(R.string.server_error)
                }
            }
           else -> this.toString()
       }
    }




}