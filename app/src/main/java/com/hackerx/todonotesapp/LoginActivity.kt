package com.hackerx.todonotesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.hackerx.todonotesapp.Adapter.AppConstants

class LoginActivity : AppCompatActivity() {

    lateinit var editTextfullname:EditText
    lateinit var  editTextusername:EditText
    lateinit var  loginbtn:Button
    lateinit var sharedPreferences:SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindViews()
        setupSharedPreferences()

    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE)

    }

    private fun bindViews() {
        editTextfullname = findViewById(R.id.edittextfullname);
        editTextusername = findViewById(R.id.edittextusername)
        loginbtn = findViewById(R.id.login_btn)
        val clickAction = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val fullname = editTextfullname.text.toString()
                val username = editTextusername.text.toString()
                if(fullname.isNotEmpty() && username.isNotEmpty())
                {
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    intent.putExtra(AppConstants.FULL_NAME,fullname)
                    startActivity(intent)
                    saveFullName(fullname)
                    saveLoginStatus()
                }
            }

        }
        loginbtn.setOnClickListener(clickAction)

    }

    private fun saveLoginStatus() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.IS_LOGGED_IN,true)
        editor.apply()
    }

    private fun saveFullName(fullname: String) {
        editor = sharedPreferences.edit()
        editor.putString(PrefConstant.FULL_NAME,fullname)
        editor.apply()

    }
}
