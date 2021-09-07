package com.example.earlylife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.earlylife.fragments.HomeFragment
import com.example.earlylife.fragments.QuiltDetailsFragment
import com.example.earlylife.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val quiltDetailsFragment = QuiltDetailsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_quilt_details -> replaceFragment(quiltDetailsFragment)

            }
            true
        }

        //val actionBarView = findViewById<MaterialToolbar>(R.id.actionBarView)
        //setSupportActionBar(actionBarView)


    }

    private fun replaceFragment (fragment: Fragment) {
        if (fragment != null) {
            val transaction= supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    }