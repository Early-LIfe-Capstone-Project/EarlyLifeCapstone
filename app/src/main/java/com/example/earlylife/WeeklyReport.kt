/**
 * This class is used to create a weekly report model which will be displayed to the user based
 * on the UsageStatistics of each Activity in the ActivityList provided to the class
 *
 */

package com.example.earlylife

class WeeklyReport {
    fun generateReport(activityList: ActivityList): Map<String, Float> {
        /**
         * Generates a report, which maps an activity to the usage pulled from its most
         * recent API pull, given an ActivityList
         *
         * @param activityList list being transformed into report
         * @return a mapping between the activity and its usage from the most recent API pull
         */
        var report = mutableMapOf<String, Float>()

        for (activity in activityList.activityList){
            report[activity.activityName] = activity.usage.weeklyUsage
        }

        return report
    }
}