package com.example.earlylife

import org.junit.Assert.*

import org.junit.Test

private const val NAME = "Colour Match"
private const val DETAILS = "Match colours on the quilt"
private const val ECD = "Cognition"
private const val TIP_1 = "Point at colours"
private const val TIP_2 = "Talk them through activity and demonstrate"
private const val TIP_3 = "Verbalize"

private const val NAME_2 = "Shape Recognition"
private const val DETAILS_2 = "Identify the shapes in the quilt as instructed"
private const val ECD_2 = "Listening and visualisation"
private const val TIP_1_2 = "Tell them the shapes before they start"
private const val TIP_2_2 = "Have them talk through all the shapes before starting"

class WeeklyReportTest {

    private val tips1 = listOf(TIP_1, TIP_2, TIP_3)
    private val tips2 = listOf(TIP_1_2, TIP_2_2)
    private val sampleActivity1 = Activity(NAME, DETAILS, ECD, tips1)
    private val sampleActivity2 = Activity(NAME_2, DETAILS_2, ECD_2, tips2)
    private val sampleUsageStatistics = UsageStatistics()
    private val sampleActivityList = ActivityList(listOf(sampleActivity1, sampleActivity2))
    private val sampleWeeklyReport = WeeklyReport()

    @Test
    fun weeklyReport_generatesReport() {
        val expected = mapOf<String, Float>(NAME to sampleUsageStatistics.weeklyUsage, NAME_2 to sampleUsageStatistics.weeklyUsage)
        assertEquals(expected, sampleWeeklyReport.generateReport(sampleActivityList))
    }
}