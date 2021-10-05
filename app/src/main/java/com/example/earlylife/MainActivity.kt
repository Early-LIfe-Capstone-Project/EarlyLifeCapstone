package com.example.earlylife

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.earlylife.fragments.HomeFragment
import com.example.earlylife.fragments.QuiltDetailsFragment
import com.example.earlylife.model.Quilt
import com.example.earlylife.retrofit.RetrofitService
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val quiltDetailsFragment = QuiltDetailsFragment()
    val appDataHandler = AppDataHandler(this)
    lateinit var activityList: ActivityList

    var downloadPermission = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(homeFragment)

        activityList = appDataHandler.loadActivityData()

        homeFragment.activityList = activityList

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

    fun checkNetwork(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        val isMetered = (context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).isActiveNetworkMetered

    }




}