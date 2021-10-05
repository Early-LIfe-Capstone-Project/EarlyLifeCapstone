package com.example.earlylife

import com.example.earlylife.model.Quilt

class UsageStatistics(var weeklyUsage: Float = 0F, var lastWeeklyUsage: Float = 0F):
    Comparable<UsageStatistics>
{
    override fun compareTo(other: UsageStatistics): Int {
        return when {
            weeklyUsage>other.weeklyUsage -> {
                1
            }
            weeklyUsage<other.weeklyUsage -> {
                -1
            }
            else -> 0
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other is UsageStatistics) &&
                (weeklyUsage==other.weeklyUsage) &&
                (lastWeeklyUsage==other.lastWeeklyUsage)
    }

    fun update(activityName: String, response: Quilt) {
        lastWeeklyUsage = weeklyUsage
        weeklyUsage = 1.2F // Example for tests
        //TODO("Pull data from API if Quilt is connected")
        if (activityName == "Learn Shapes"){
            weeklyUsage = response.LearnShapes.timeOnTask.toFloat()
        }

        else if (activityName == "Love"){
            weeklyUsage = response.Love.timeOnTask.toFloat()
        }

        else if (activityName == "Learn Numbers"){
            weeklyUsage = response.LearnNumbers.timeOnTask.toFloat()
        }

        else if (activityName == "Match Shapes"){
            val weeklyUsage = response.MarchShapes.timeOnTask
        }

    }

    override fun toString(): String {
        return ("$lastWeeklyUsage,$weeklyUsage")
    }
}