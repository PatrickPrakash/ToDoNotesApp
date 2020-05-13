package com.hackerx.todonotesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        setupLoginSetup()
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE)
    }

    private fun setupLoginSetup() {
        val isLoggedin = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN,false);
        if(isLoggedin) {
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@SplashActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}