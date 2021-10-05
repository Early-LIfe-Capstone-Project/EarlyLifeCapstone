package com.example.earlylife

class ActivityList constructor (var activityList: List<Activity>) {
    var totalTime = 0F
    var lastTotalTime = 0F

    init {
        totalTime = getWeeklyUsage().sum()
        lastTotalTime = getLastWeeklyUsage().sum()
    }

    fun update(){
        for (activity in activityList){
            activity.update()
        }
    }

    fun getActivityNames(): List<String>{
        val activityNames = mutableListOf<String>()

        for (activity in activityList){
            activityNames.add(activity.activityName)
        }

        return activityNames
    }

    fun getWeeklyUsage(): List<Float>{
        val weeklyUsages = mutableListOf<Float>()

        for (activity in activityList){
            weeklyUsages.add(activity.usage.weeklyUsage)
        }

        return weeklyUsages
    }

    fun getLastWeeklyUsage(): List<Float>{
        val lastWeeklyUsages = mutableListOf<Float>()

        for (activity in activityList){
            lastWeeklyUsages.add(activity.usage.lastWeeklyUsage)
        }

        return lastWeeklyUsages
    }

    fun getActivity(index: Int): Activity{
        return activityList[index]
    }

    fun getActivity(name: String): Activity{
        val activityNames = getActivityNames()
        val index = activityNames.indexOf(name)

        return activityList[index]
    }
}