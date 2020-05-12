package com.hackerx.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hackerx.todonotesapp.Adapter.NotesAdapter;

import java.util.ArrayList;

import ClickListener.ItemClickListener;

import static com.hackerx.todonotesapp.Adapter.AppConstants.DESCRIPTION;
import static com.hackerx.todonotesapp.Adapter.AppConstants.TITLE;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabNotes;
    SharedPreferences sharedPreferences;
    RecyclerView todorecycler;
    ArrayList<Notes> notesArrayList = new ArrayList<>();
    String TAG = "mainactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fabNotes = findViewById(R.id.floatactionbtn);
        todorecycler = findViewById(R.id.todorecycler);
        Intent intent = getIntent();
        String fullname = intent.getStringExtra("full_name");
        getSupportActionBar().setTitle(fullname);
        fabNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupDialogbox();
            }

        });
    }

    private void setupSharedprefernces() {

    }


    private void  setupDialogbox() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.notes_dialog,null);

        final EditText editTextTitle = view.findViewById(R.id.edittexttitle);
        final EditText editTextDesc = view.findViewById(R.id.edittextdesc);

        final Button dialogbtn = view.findViewById(R.id.submit_btn);

        final Dialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String description = editTextDesc.getText().toString();
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description))
                {
                    Notes notes = new Notes();
                    notes.setTitle(title);
                    notes.setDescription(description);
                    notesArrayList.add(notes);
                    alertDialog.hide();
                    setupRecyclerView();
                }
                else{
                    Toast.makeText(MainActivity.this,"Text Fields cannot be blank",Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();

    }

    public void setupRecyclerView()
    {
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(Notes notes) {

               Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
               intent.putExtra(TITLE,notes.getTitle());
               intent.putExtra(DESCRIPTION,notes.getDescription());
               startActivity(intent);
            }

        };
        NotesAdapter notesAdapter = new NotesAdapter(notesArrayList,itemClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        todorecycler.setLayoutManager(linearLayoutManager);
        todorecycler.setAdapter(notesAdapter);
    }


}