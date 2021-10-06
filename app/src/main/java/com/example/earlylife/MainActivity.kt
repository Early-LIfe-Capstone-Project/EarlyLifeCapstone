package com.example.earlylife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.earlylife.fragments.HomeFragment
import com.example.earlylife.fragments.QuiltDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * This class represents the Main Activity of the application and contains the Bottom Navigation View
 * It switches between the Home Fragment and Quilt Details Fragment
 * depending on the icon clicked by the user.
 *
 */
class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val quiltDetailsFragment = QuiltDetailsFragment()
    private val appDataHandler = AppDataHandler(this)
    lateinit var activityList: ActivityList

    var downloadPermission = true

    override fun onCreate(savedInstanceState: Bundle?) {

        /**
         * On Create creates the Bottom Navigation View and sets the View to the Home Fragment.
         * It also contains the logic to swap between the Home Fragment and the Quilt Details fragment.
         *
         */

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(homeFragment)

        activityList = appDataHandler.loadActivityData()

        homeFragment.activityList = activityList
        homeFragment.appDataHandler = appDataHandler

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_quilt_details -> replaceFragment(quiltDetailsFragment)
            }
            true
        }

        activityList = appDataHandler.loadActivityData()
    }

    private fun replaceFragment (fragment: Fragment) {
        /**
         * Replace Fragment replaces the fragment container with the given fragment.
         * @param fragment
         */

        if (fragment != null) {
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }




}