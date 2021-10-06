package com.example.earlylife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.earlylife.fragments.HomeFragment
import com.example.earlylife.fragments.QuiltDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val quiltDetailsFragment = QuiltDetailsFragment()
    private val appDataHandler = AppDataHandler(this)
    lateinit var activityList: ActivityList

    var downloadPermission = true

    override fun onCreate(savedInstanceState: Bundle?) {
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
        if (fragment != null) {
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }




}