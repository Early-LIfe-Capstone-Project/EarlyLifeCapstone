package com.example.earlylife

import org.junit.Assert.*

import org.junit.Test

class UsageStatisticsTest {

    private val sampleUsageStatistics = UsageStatistics()

    @Test
    fun usageStats_getsWeeklyUsage() {
        assertEquals(0F, sampleUsageStatistics.weeklyUsage)
    }

    @Test
    fun usageStats_setsWeeklyUsage() {
        val changedUsageStatistics = sampleUsageStatistics
        changedUsageStatistics.weeklyUsage = 2.0F
        assertEquals(2.0F, changedUsageStatistics.weeklyUsage)
    }

    @Test
    fun usageStats_updatesStats() {
        val changedUsageStatistics = sampleUsageStatistics
        changedUsageStatistics.update(activityName, response)
        val expected = UsageStatistics(lastWeeklyUsage = 0F, weeklyUsage = 1.2F)
        assertEquals(expected, changedUsageStatistics)
    }
}