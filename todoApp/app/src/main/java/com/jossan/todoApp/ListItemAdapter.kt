package com.jossan.todoApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class ListItemAdapter(private var itemsList: ArrayList<ListItem>)
    : RecyclerView.Adapter<ListItemAdapter.MyViewHolder> () {

    var onItemClick : ((ListItem) -> Unit)? = null

    internal inner class MyViewHolder(view: View):
        RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = itemsList[position]
        holder.itemTextView.text = listItem.name

        holder.itemTextView.setOnClickListener() {
            // println(listItem.name)
            onItemClick?.invoke(listItem)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}