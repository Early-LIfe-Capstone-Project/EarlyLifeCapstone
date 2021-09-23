package com.example.earlylife

import org.junit.Assert.*
import org.junit.Test

private const val NAME = "Colour Match"
private const val DETAILS = "Match colours on the quilt"
private const val ECD = "Cognition"
private const val TIP_1 = "Point at colours"
private const val TIP_2 = "Talk them through activity and demonstrate"
private const val TIP_3 = "Verbalize"

class ActivityTest {

    private val tips = listOf(TIP_1, TIP_2, TIP_3)
    private val sampleActivity = Activity(NAME, DETAILS, ECD, tips)
    private val sampleUsageStatistics = UsageStatistics()

    @Test
    fun activity_checkUndeclaredUsageStatistics() {
        assertEquals(sampleUsageStatistics, sampleActivity.usage)
    }

    @Test
    fun activity_generatesTip() {
        assertTrue(tips.contains(sampleActivity.generateTip()))
    }

    @Test
    fun activity_updatesStats() {
        val changedActivity = sampleActivity
        val changedUsage = sampleUsageStatistics
        changedActivity.update()
        changedUsage.update()
        assertEquals(sampleUsageStatistics, sampleActivity.usage)
    }

    @Test
    fun activity_getsActivityName() {
        assertEquals(NAME, sampleActivity.activityName)
    }

    @Test
    fun activity_setsActivityName() {
        val changedActivity = sampleActivity
        val expected = "Colour Match Game"
        changedActivity.activityName = expected
        assertEquals(expected, changedActivity.activityName)
    }

    @Test
    fun activity_getsActivityDetails() {
        assertEquals(DETAILS, sampleActivity.activityDetails)
    }

    @Test
    fun activity_setsActivityDetails() {
        val changedActivity = sampleActivity
        val expected = "Match the quilt squares of the same colour"
        changedActivity.activityDetails = expected
        assertEquals(expected, changedActivity.activityDetails)
    }

    @Test
    fun activity_getsEcdDetails() {
        assertEquals(ECD, sampleActivity.ecdDetails)
    }

    @Test
    fun activity_setsEcdDetails() {
        val changedActivity = sampleActivity
        val expected = "This will help your child practice their cognition skills"
        changedActivity.ecdDetails = expected
        assertEquals(expected, changedActivity.ecdDetails)
    }
}
