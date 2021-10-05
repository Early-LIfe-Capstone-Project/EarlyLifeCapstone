/**
 * This class is used to represent all the quilt activities hosted on the quilt which will be stored in the main activity
 * It also contains methods to update activities when data is pulled from the quilt API
 *
 * @constructor the primary constructor takes in a list of Activity objects. The total time spent with the quilt for the most recent
 * API pull and for the last API pull is set to 0 by default. The init block is triggered which calculates these totals.
 */

package com.example.earlylife

import com.example.earlylife.model.Quilt

class ActivityList constructor (var activityList: List<Activity>) {
    var totalTime = 0F
    var lastTotalTime = 0F

    init {
        /**
         * Init block which sets the total time spent with the quilt for the most recent API pull and the last API pull.
         * Cycles through all the activities' usage statistics and adds all the data
         */
        totalTime = getWeeklyUsage().sum()
        lastTotalTime = getLastWeeklyUsage().sum()
    }

    fun update(response: Quilt) {
        /**
         * Updates the usage statistics of all the quilt activities using data pulled from the quilts API by
         * looping through all the activities in the list and calling the update method for each.
         *
         * @param response Quilt Object containing the response from the API call to the quilt
         */
        for (activity in activityList){
            activity.update(response)
        }
    }

    fun getActivityNames(): List<String>{
        /**
         * Generates a list of all the quilt activity names
         *
         * @return list of activity names
         */
        val activityNames = mutableListOf<String>()

        for (activity in activityList){
            activityNames.add(activity.activityName)
        }

        return activityNames
    }

    private fun getWeeklyUsage(): List<Float>{
        /**
         * Generates a list of the usage statistics for each activity from the most recent API pull
         * Used to calculate the total time spent on quilt for the most recent API pull
         *
         * @return list of times for each activity
         */
        val weeklyUsages = mutableListOf<Float>()

        for (activity in activityList){
            weeklyUsages.add(activity.usage.weeklyUsage)
        }

        return weeklyUsages
    }

    private fun getLastWeeklyUsage(): List<Float>{
        /**
         * Generates a list of the usage statistics for each activity from the last API pull
         * Used to calculate the total time spent on quilt for the last API pull
         *
         * @return list of times for each activity
         */
        val lastWeeklyUsages = mutableListOf<Float>()

        for (activity in activityList){
            lastWeeklyUsages.add(activity.usage.lastWeeklyUsage)
        }

        return lastWeeklyUsages
    }

    fun getActivity(index: Int): Activity{
        /**
         * Gets an element in the activity list at the provided index
         *
         * @return Activity at the index
         * @param index an index in the array which must be less than the length of the array
         */
        return activityList[index]
    }

    fun getActivity(name: String): Activity{
        /**
         * Gets the corresponding activity given the activity name
         *
         * @return Activity with the given name
         * @param name the name associated with the Activity object being requested
         */
        val activityNames = getActivityNames()
        val index = activityNames.indexOf(name)

        return activityList[index]
    }
}