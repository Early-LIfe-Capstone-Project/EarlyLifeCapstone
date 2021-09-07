package com.example.earlylife

class Activity constructor (
            var activityName: String,
            var activityDetails: String,
            var ecdDetails: String,
            private var tips: List<String>
        ) {

    fun generateTip(): String{
        return tips.random()
    }

}