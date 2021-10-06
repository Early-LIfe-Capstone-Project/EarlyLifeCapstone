/**
 * This class represents the usage statistics of a quilt activity. It implements the comparable
 * interface for testing purposes and so that activities can be compared. The update method pulls
 * data from the API response to update the usage statistics of an activity based on the quilt response.
 *
 * @constructor primary constructor takes in the weekly usage and past weekly usage value, if not
 * provided, these values are set to zero to connote an empty Usage Statistics object
 */
package com.example.earlylife

import com.example.earlylife.model.Quilt

class UsageStatistics(var weeklyUsage: Float = 50F, var lastWeeklyUsage: Float = 50F):
    Comparable<UsageStatistics>
{
    override fun compareTo(other: UsageStatistics): Int {
        /**
         * Overrides the compareTo method from the comparable interface. The method compares the
         * weekly usage value of this UsageStatistic object and the given one. If they are equal, 0
         * is returned, if the given weekly usage is less than this weekly usage, a positive value
         * is returned and if the given weekly usage is more that this weekly usage, a negative
         * value is returned.
         *
         * @param other the other UsageStatistic object being compared to.
         * @return an integer denoting whether the other object is more than, less than or equal to
         * this object.
         */
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
        /**
         * Overrides default equals method to check whether an object is equal to this
         * UsageStatistics object.
         * This will be true if all attributes are equal.
         * Used for testing purposes.
         *
         * @param other the object being compared to the current UsageStatics object.
         * @return boolean stating whether or not the object equals this UsageStatistic
         */
        return (other is UsageStatistics) &&
                (weeklyUsage==other.weeklyUsage) &&
                (lastWeeklyUsage==other.lastWeeklyUsage)
    }

    fun update(activityName: String, response: Quilt) {
        /**
         * Update method which uses the response from the API call to update the usage statistics of
         * the quilt activity. (Dylan please complete ily bestie)
         *
         * @param activityName
         * @param response
         */
        lastWeeklyUsage = weeklyUsage

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
            weeklyUsage = response.MarchShapes.timeOnTask.toFloat()
        }

    }

    override fun toString(): String {
        /**
         * Overrides the default toString method. Written for the use of writing activity data to
         * text file in the necessary convention
         *
         * @return String containing the two usage statistic values separated by a comma
         */
        return ("$lastWeeklyUsage,$weeklyUsage")
    }
}