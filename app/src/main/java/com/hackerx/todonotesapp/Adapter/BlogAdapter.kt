package com.hackerx.todonotesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.hackerx.todonotesapp.Model.Data
import com.hackerx.todonotesapp.R

class BlogAdapter(val list:List<Data>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView = itemView.findViewById(R.id.blog_imageview)
        val heading:MaterialTextView = itemView.findViewById(R.id.heading_blog)
        val description:MaterialTextView = itemView.findViewById(R.id.description_blog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blogs_adapter_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BlogAdapter.ViewHolder, position: Int) {
        val blog = list[position]
        Glide.with(holder.itemView).load(blog.img_url).into(holder.imageView)
        holder.heading.text = blog.title
        holder.description.text = blog.description
    }

}