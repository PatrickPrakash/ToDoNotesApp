package com.hackerx.todonotesapp.WorkManager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.hackerx.todonotesapp.NotesApp
import com.hackerx.todonotesapp.db.NotesDao

class MyWorker(val context: Context, val workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    override fun doWork(): Result {
        val NotesApp = applicationContext as NotesApp
        val NotesDao = NotesApp.getNotesdb().notesDao()
        NotesDao.deleteNotes(true)
        return Result.success()
    }

}