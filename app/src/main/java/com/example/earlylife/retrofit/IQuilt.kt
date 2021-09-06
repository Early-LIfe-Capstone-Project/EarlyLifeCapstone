package com.example.earlylife.retrofit

import com.example.earlylife.model.Quilt
import io.reactivex.Observable
import retrofit2.http.GET

interface IQuilt
{

    @GET("/")
    fun getShapes(): Observable<Quilt>

    @GET("/")
    fun getLove(): Observable<Quilt>


}