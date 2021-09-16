package com.example.earlylife.classes

class ActivityList constructor (var activityList: List<Activity>){
    fun update(){
        for (activity in activityList){
            activity.update()
        }
    }
}