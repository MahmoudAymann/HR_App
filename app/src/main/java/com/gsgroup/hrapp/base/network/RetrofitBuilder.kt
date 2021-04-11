package com.gsgroup.hrapp.base.network

import com.google.gson.GsonBuilder
import com.gsgroup.hrapp.BuildConfig
import com.gsgroup.hrapp.data.remote.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mabaat.androidapp.base.network.NetworkResponseAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val BASE_URL =
    "http://142.93.226.68/api/"

/*
*add this if you want a static header in all requests
**/
fun getHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request =
            chain.request().newBuilder()
                .header("jwt", "")
                .header("lang", "")
                .build()
        chain.proceed(request)
    }
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                this.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            readTimeout(120, TimeUnit.SECONDS)
            connectTimeout(120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
        }
        .build()
}


private val retrofitBuilder = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addCallAdapterFactory(NetworkResponseAdapterFactory())
    .baseUrl(BASE_URL)
    .client(createOkHttpClient())
    .build()


object RetrofitBuilder {
    val API_SERVICE: ApiService by lazy {
        retrofitBuilder.create(ApiService::class.java)
    }
}
