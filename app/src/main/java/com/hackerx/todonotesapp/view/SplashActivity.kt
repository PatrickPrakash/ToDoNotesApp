package com.hackerx.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.hackerx.todonotesapp.Utlis.PrefConstant
import com.hackerx.todonotesapp.R

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences
    val TAG = "Splash Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        setupLoginSetup()
        getFCMToken()
    }

    private fun getFCMToken() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    //Log.d(TAG, "FCM Get token")
                   // Toast.makeText(baseContext,token, Toast.LENGTH_SHORT).show()
                })
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE)
    }

    private fun setupLoginSetup() {
        val isLoggedin = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN,false);
        if(isLoggedin) {
            val intent = Intent(this@SplashActivity, NotesActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}