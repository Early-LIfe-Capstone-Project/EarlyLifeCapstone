package com.example.earlylife

import com.example.earlylife.model.*
import org.junit.Assert.*
import org.junit.Test

private const val NAME = "Love"
private const val DETAILS = "Match colours on the quilt"
private const val ECD = "Cognition"
private const val TIP_1 = "Point at colours"
private const val TIP_2 = "Talk them through activity and demonstrate"
private const val TIP_3 = "Verbalize"

private const val NAME_2 = "Match Shapes"
private const val DETAILS_2 = "Identify the shapes in the quilt as instructed"
private const val ECD_2 = "Listening and visualisation"
private const val TIP_1_2 = "Tell them the shapes before they start"
private const val TIP_2_2 = "Have them talk through all the shapes before starting"

private val response = Quilt(
    LearnNumbers("", "","","",""),
    LearnShapes("","","","",""),
    Love("", "","","","3.4"),
    MarchShapes("","","","","1.2")
)

class ActivityListTest {

    private val tips1 = listOf(TIP_1, TIP_2, TIP_3)
    private val tips2 = listOf(TIP_1_2, TIP_2_2)
    private val sampleActivity1 = Activity(NAME, DETAILS, ECD, tips1)
    private val sampleActivity2 = Activity(NAME_2, DETAILS_2, ECD_2, tips2)
    private val sampleUsageStatistics = UsageStatistics()
    private val sampleActivityList = ActivityList(listOf(sampleActivity1, sampleActivity2))

    @Test
    fun activityList_updatesStats() {
        val changedActivity1 = sampleActivity1
        changedActivity1.update(response)

        val changedActivity2 = sampleActivity2
        changedActivity2.update(response)

        val expected = listOf(changedActivity1, changedActivity2)

        val changedActivityList = sampleActivityList
        changedActivityList.update(response)
        assertEquals(expected, changedActivityList.activityList)
    }

    @Test
    fun activityList_getsActivity() {
        assertEquals(sampleActivity1, sampleActivityList.getActivity(0))
    }
}