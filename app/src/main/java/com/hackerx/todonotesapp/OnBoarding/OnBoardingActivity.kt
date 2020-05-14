package com.hackerx.todonotesapp.OnBoarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.hackerx.todonotesapp.R
import com.hackerx.todonotesapp.Utlis.PrefConstant
import com.hackerx.todonotesapp.view.LoginActivity
import com.hackerx.todonotesapp.view.SplashActivity
import java.lang.Exception

class OnBoardingActivity : AppCompatActivity(), OnBoardingFirstFragment.OnNextClick,OnBoardingTwoFragment.onOptionClick {
    lateinit var viewpager: ViewPager2
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        bindView()
        setupSharedPreferences()
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE)
    }

    private fun bindView() {
        viewpager = findViewById(R.id.viewpager)
        val adapter = FragmentAdapter(this)
        viewpager.adapter = adapter
    }

    override fun onselectClick() {
        try {
            viewpager.setCurrentItem(1)
        } catch (e : Exception){
            Log.d("OnBoard", e.toString())
        }

    }

    override fun onOptionBack() {
        try {
            viewpager.setCurrentItem(0)
        } catch (e : Exception){
            Log.d("OnBoard", e.toString())
        }
    }

    override fun onOptionNext() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.ONBOARDED_SUCCESFULLY,true)
        editor.apply()
        val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
        startActivity(intent)
    }

}