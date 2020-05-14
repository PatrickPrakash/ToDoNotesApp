package com.hackerx.todonotesapp.ClickListener

import com.hackerx.todonotesapp.db.Notes

interface ItemClickListener {
    fun onClick(notes : Notes)
    fun onUpdate(notes: Notes)
}