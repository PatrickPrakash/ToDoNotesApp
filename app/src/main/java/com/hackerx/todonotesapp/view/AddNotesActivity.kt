package com.hackerx.todonotesapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.hackerx.todonotesapp.BuildConfig
import com.hackerx.todonotesapp.R
import com.hackerx.todonotesapp.Utlis.AppConstants
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddNotesActivity : AppCompatActivity() {
    lateinit var editTextTitle: EditText
    lateinit var ediTextDescription: EditText
    lateinit var imageViewNotes : ImageView
    lateinit var submitButton : MaterialButton
    val REQUEST_CODE_GALLERY = 2
    val REQUEST_CODE_CAMERA = 4
    var picture_path = ""
    var TAG = "ADDNOTESACTIVITY"
    var MY_PERMISSION_CODE = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        bindViews()
        clickListener()
    }

    private fun clickListener() {
        imageViewNotes.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                if(checkAndRequestPermission())
                {
                    setupDialog()
                }

            }

        })
        submitButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent()
                intent.putExtra(AppConstants.TITLE,editTextTitle.text.toString())
                intent.putExtra(AppConstants.DESCRIPTION,ediTextDescription.text.toString())
                intent.putExtra(AppConstants.IMAGE_PATH,picture_path)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        })
    }

    private fun checkAndRequestPermission(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        val externalPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val listPermissionNeeded = ArrayList<String>()
        if(cameraPermission !=PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(android.Manifest.permission.CAMERA)
        }
        if(externalPermission !=PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if(listPermissionNeeded.isNotEmpty()){
            ActivityCompat.requestPermissions(this,listPermissionNeeded.toTypedArray<String>(),MY_PERMISSION_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            MY_PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    setupDialog()
                }
            }
        }
    }

    private fun setupDialog() {
        val view  = LayoutInflater.from(this).inflate(R.layout.dialog_image_selector,null)
        val textViewCamera: TextView = view.findViewById(R.id.textviewCamera)
        val textViewGallery :TextView = view.findViewById(R.id.textviewGallery)
        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(true)
                .create()
        textViewCamera.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val takePictureintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                var photoFile : File? = null
                photoFile = createImage()
                if(photoFile !=null)
                {
                    val photoUri = FileProvider.getUriForFile(this@AddNotesActivity,BuildConfig.APPLICATION_ID+".provider",photoFile)
                    picture_path = photoFile.absolutePath
                    takePictureintent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                    startActivityForResult(takePictureintent,REQUEST_CODE_CAMERA)
                    dialog.hide()
                }
            }

        })
        textViewGallery.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,REQUEST_CODE_GALLERY)
            }

        })
        dialog.show()
    }

    private fun createImage(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val fileName = "JPEG" + timeStamp + " "
        val storgaeDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",storgaeDir)
    }

    private fun bindViews() {
        editTextTitle = findViewById(R.id.edittexttitle)
        ediTextDescription = findViewById(R.id.edittextdesc)
        imageViewNotes = findViewById(R.id.notesimageview)
        submitButton = findViewById(R.id.submit_btn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                REQUEST_CODE_GALLERY -> {
                    val selectedImage = data?.data
                    val filePath = arrayOf(MediaStore.Images.Media.DATA)
                    val c = contentResolver.query(selectedImage!!,filePath,null,null,null)
                    c!!.moveToFirst()
                    val columnIndex = c.getColumnIndex(filePath[0])
                    picture_path = c.getString(columnIndex)
                    c.close()
                    Glide.with(this).load(picture_path).into(imageViewNotes)
                }
                REQUEST_CODE_CAMERA -> {
                    Glide.with(this).load(picture_path).into(imageViewNotes)
                }

            }
        }

    }
}