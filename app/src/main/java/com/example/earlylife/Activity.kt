package com.example.earlylife

class Activity constructor (
            var activityName: String,
            var activityDetails: String,
            var ecdDetails: String,
            private var tips: List<String>
        ) {

    var usage: UsageStatistics = UsageStatistics()

    fun update(){
        usage.update()
    }

    fun generateTip(): String{
        return tips.random()
    }

}