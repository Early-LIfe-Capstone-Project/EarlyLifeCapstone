package com.example.earlylife

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.earlylife.model.Quilt
import com.example.earlylife.retrofit.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class DataHandler {
    fun handle (){
        val permission = true

        checkPermission()

        if (permission) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitService.ServiceBuilder.buildService().getShapes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { response -> onResponse(response) })}
    }

        private fun onResponse(response: Quilt)
        {

            var txt_activityID= response.LearnShapes.activityID
            var txt_activityName = response.LearnShapes.activityName
            var txt_timeOnTask = response.LearnShapes.timeOnTask
            var txt_correct = response.LearnShapes.correct
            var txt_date = response.LearnShapes.date


        }

        fun checkPermission(){


        }
    }