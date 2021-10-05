package com.example.earlylife.retrofit

import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService
{
    object ServiceBuilder {
        private val client = OkHttpClient
            .Builder()
            .connectTimeout(5,TimeUnit.MINUTES)
            .readTimeout(2,TimeUnit.MINUTES)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.8.1")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(IQuilt::class.java)


        fun buildService(): IQuilt {
            return retrofit
        }
    }

}