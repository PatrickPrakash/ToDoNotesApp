package com.hackerx.todonotesapp

import android.app.Application
import com.hackerx.todonotesapp.db.NotesDatabase

class NotesApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }
    fun getNotesdb():NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}