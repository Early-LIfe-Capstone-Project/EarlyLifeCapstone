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
        usage.update(activityName,response)
    }

    fun generateTip(): String{
        return tips.random()
    }

    override fun equals(other: Any?): Boolean {
        return (other is Activity) &&
                (activityName == other.activityName) &&
                (activityDetails == other.activityDetails) &&
                (ecdDetails == other.ecdDetails) &&
                (tips == other.tips) &&
                (usage == other.usage)
    }

}