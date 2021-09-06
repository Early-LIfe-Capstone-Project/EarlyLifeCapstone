package com.example.earlylife.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    object ServiceBuilder {
        private val client = OkHttpClient
            .Builder()
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