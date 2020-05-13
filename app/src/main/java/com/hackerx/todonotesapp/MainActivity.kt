package com.hackerx.todonotesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hackerx.todonotesapp.Adapter.AppConstants
import com.hackerx.todonotesapp.Adapter.NotesAdapter
import com.hackerx.todonotesapp.ClickListener.ItemClickListener

class MainActivity : AppCompatActivity() {
    private var fullname:String? = ""
    private lateinit var fabNotes:FloatingActionButton
    lateinit var todorecycler:RecyclerView
    lateinit var sharedPreferences: SharedPreferences
    var notesArrayList = ArrayList<Notes>()
    val TAG = "Main Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        setupSharedPreferences()
        getIntentData()
        supportActionBar?.title = fullname
        fabNotes.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                setupDialogbox()
            }

        })
    }

    private fun setupDialogbox() {
        val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.notes_dialog,null)
        val editTexttitle = view.findViewById<EditText>(R.id.edittexttitle)
        val edittextDescription = view.findViewById<EditText>(R.id.edittextdesc)
        val buttonsubmit = view.findViewById<MaterialButton>(R.id.submit_btn)
        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
       buttonsubmit.setOnClickListener(object : View.OnClickListener{
           override fun onClick(v: View?) {
               val title = editTexttitle.text.toString()
               val description = editTexttitle.text.toString()
               if(title.isNotEmpty() && description.isNotEmpty())
               {
                   val notes = Notes(title, description)
                   notesArrayList.add(notes)
               }
               else{
                   Toast.makeText(this@MainActivity,"Title and description Cannot be Empty",Toast.LENGTH_SHORT).show()
               }
               setupRecyclerView()
               dialog.hide()
           }

       })
        dialog.show()
    }

    private fun setupRecyclerView() {
        val itemClickListener = object : ItemClickListener {
            override fun onClick(notes: Notes) {
                val intent = Intent(this@MainActivity,DetailsActivity::class.java)
                intent.putExtra(AppConstants.TITLE,notes.title)
                intent.putExtra(AppConstants.DESCRIPTION,notes.description)
                startActivity(intent)
            }

        }
        val notesAdapter = NotesAdapter(notesArrayList,itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        todorecycler.layoutManager = linearLayoutManager
        todorecycler.adapter = notesAdapter

    }

    private fun getIntentData() {
            val intent = intent
            if (intent.hasExtra(AppConstants.FULL_NAME))
            {
                fullname = intent.getStringExtra(AppConstants.FULL_NAME)
            }

            if(fullname!!.isEmpty())
            {
                fullname = sharedPreferences.getString(PrefConstant.FULL_NAME,"")
            }
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE)
    }

    private fun bindViews() {
        fabNotes = findViewById(R.id.floatactionbtn)
        todorecycler = findViewById(R.id.todorecycler)
    }
}