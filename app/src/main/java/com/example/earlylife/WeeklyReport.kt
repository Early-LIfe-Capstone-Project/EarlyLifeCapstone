package com.example.earlylife

class WeeklyReport {
    fun generateReport(activityList: ActivityList): Map<String, Float> {
        var report = mutableMapOf<String, Float>()

        for (activity in activityList.activityList){
            report[activity.activityName] = activity.usage.weeklyUsage
        }

        return report
    }
}