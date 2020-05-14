package com.hackerx.todonotesapp

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.hackerx.todonotesapp.db.NotesDatabase

class NotesApp : Application(){
    override fun onCreate() {
        AndroidNetworking.initialize(applicationContext);

        super.onCreate()
    }
    fun getNotesdb():NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}