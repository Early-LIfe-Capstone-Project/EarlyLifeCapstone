/**
 * This class is used to represent a quilt activity and store the usage data pertaining to that activity
 * It also contains methods to update usage statistics when data is pulled from the quilt API
 *
 * @constructor the primary constructor takes in the activity name, details, ecd resources tips and usage data to initialize the Activity
 */

package com.example.earlylife

import com.example.earlylife.model.Quilt

class Activity constructor(
            var activityName: String,
            var activityDetails: String,
            var ecdDetails: String,
            var tips: List<String>,
            var usage: UsageStatistics = UsageStatistics()
        ){

    fun update(response: Quilt) {
        /**
         * Updates the usage statistics of this activity using data pulled from the quilts API
         *
         * @param response Quilt Object containing the response from the API call to the quilt
         */
        usage.update(activityName,response)
    }

    fun generateTip(): String{
        /**
         * Generates a random tip from the list of tips which will be used for display purposes
         *
         * @return a string which is a random tip from the tips array
         */
        return tips.random()
    }

    override fun equals(other: Any?): Boolean {
        /**
         * Overrides default equals method to check whether an object is equal to this activity.
         * This will be true if all attributes are equal.
         * Used for testing purposes.
         *
         * @param other the object being compared to the current activity
         * @return boolean stating whether or not the object equals this activity
         */
        return (other is Activity) &&
                (activityName == other.activityName) &&
                (activityDetails == other.activityDetails) &&
                (ecdDetails == other.ecdDetails) &&
                (tips == other.tips) &&
                (usage == other.usage)
    }

}