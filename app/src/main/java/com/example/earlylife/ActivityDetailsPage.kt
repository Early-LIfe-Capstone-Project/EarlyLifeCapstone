/**
 * This class represents the ActivityDetailsPage which shows the user more details about the
 * selected activity on the Quilt Details Fragment. It is customized depending on which activity was
 * selected.
 */

package com.example.earlylife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

const val EXTRA_MESSAGE = "com.example.earlylife.ACTIVITY"

class ActivityDetailsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Specifies the activity details on the activity based on the activity that was selected
         * which is passed through when the activity is created via an intent object.
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)

        // Get the Intent that started this activity and extract the string array
        val message = intent.getStringArrayExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val activityName = findViewById<TextView>(R.id.activity_name).apply {
            text = message!![0]
        }
        val activityDetails = findViewById<TextView>(R.id.activity_details).apply {
            text = message!![1]
        }
        val ecdDetails = findViewById<TextView>(R.id.ecd_resource).apply {
            text = message!![2]
        }
        val tip = findViewById<TextView>(R.id.tips).apply {
            text = message!![3]
        }
    }
}