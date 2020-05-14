package com.hackerx.todonotesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView
import com.hackerx.todonotesapp.ClickListener.ItemClickListener
import com.hackerx.todonotesapp.db.Notes
import com.hackerx.todonotesapp.R

class NotesAdapter(val list: List<Notes>, val itemClickListener:ItemClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val texttitle:MaterialTextView = itemView.findViewById(R.id.card_title_text)
        val textdescription:MaterialTextView = itemView.findViewById(R.id.card_desc_text)
        val checkBoxMarkStatus:MaterialCheckBox = itemView.findViewById(R.id.card_checkbox)
        val imageview : ImageView = itemView.findViewById(R.id.card_imageview)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val notes  = list[position]
        val title = notes.title
        val description = notes.description
        //bind data to the recyclerview adapter
        holder.texttitle.text = title
        holder.textdescription.text = description
        holder.checkBoxMarkStatus.isChecked = notes.isTaskCompleted
        Glide.with(holder.itemView).load(notes.imagePath).into(holder.imageview)
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                itemClickListener.onClick(notes)
            }

        })
        holder.checkBoxMarkStatus.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                notes.isTaskCompleted = isChecked
                itemClickListener.onUpdate(notes)
            }

        })

    }

}