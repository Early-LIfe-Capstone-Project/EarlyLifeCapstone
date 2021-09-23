package com.example.earlylife

import com.example.earlylife.Activity

class ActivityList constructor (var activityList: List<Activity>){
    fun update(){
        for (activity in activityList){
            activity.update()
        }
    }

    fun getActivity(index: Int): Activity{
        return activityList[index]
    }
}