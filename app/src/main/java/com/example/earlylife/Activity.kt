package com.example.earlylife

class Activity constructor (
            var activityName: String,
            var activityDetails: String,
            var ecdDetails: String,
            private var tips: List<String>,
            var usage: UsageStatistics = UsageStatistics()
        ) {

    fun update(){
        usage.update()
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