package com.gsgroup.hrapp.base.network

import android.app.Application
import com.google.gson.GsonBuilder
import com.gsgroup.hrapp.BuildConfig
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.remote.ApiService
import com.gsgroup.hrapp.ui.fragment.login.DataUser
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefLanguage
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.gsgroup.hrapp.util.SharedPrefUtil.getPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL =
    "http://104.248.89.169:8000/api/"

/*
*add this if you want a static header in all requests
**/

fun getHeaderInterceptor(app: Application): Interceptor {
    return Interceptor { chain ->
        val token = app.getPrefs<DataUser?>(ConstString.PREF_USER_DATA)?.token
        val request =
            chain.request().newBuilder()
                .header("Authorization", "Bearer $token")
                .header("lang", app.getPrefLanguage())
                .build()
        chain.proceed(request)
    }
}

private fun createOkHttpClient(app: Application): OkHttpClient {
    return OkHttpClient.Builder()
        .apply {
            addInterceptor(getHeaderInterceptor(app))
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            readTimeout( 120, TimeUnit.SECONDS)
            connectTimeout(120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }
        .build()
}


private fun retrofitBuilder(app: Application) = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(createOkHttpClient(app))
    .build()

class RetrofitBuilder(val app: Application) {
    val apiService: ApiService by lazy {
         retrofitBuilder(app).create(ApiService::class.java)
    }
}
