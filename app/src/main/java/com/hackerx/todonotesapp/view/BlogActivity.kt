package com.hackerx.todonotesapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.hackerx.todonotesapp.Adapter.BlogAdapter
import com.hackerx.todonotesapp.Model.Data
import com.hackerx.todonotesapp.Model.JsonResponse
import com.hackerx.todonotesapp.R
import org.json.JSONArray


class BlogActivity : AppCompatActivity() {
    lateinit var recyclerblog:RecyclerView
    val TAG = "BlogActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindViews()
        getBlogs()
    }

    private fun getBlogs() {
        AndroidNetworking.get("http://www.mocky.io/v2/5926ce9d11000096006ccb30")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(JsonResponse::class.java,object : ParsedRequestListener<JsonResponse>{
                    override fun onResponse(response: JsonResponse?) {
                        setupRecyclerView(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.d(TAG, anError.toString())
                    }

                })
    }

    private fun setupRecyclerView(response:JsonResponse?) {
        val blogAdapter = BlogAdapter(response!!.data)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerblog.layoutManager = linearLayoutManager
        recyclerblog.adapter = blogAdapter
    }

    private fun bindViews() {
        recyclerblog = findViewById(R.id.blog_recyclerview)
    }


}