package com.jossan.todoApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class ItemAdapter (private var items: ArrayList<Item>)
    : RecyclerView.Adapter<ItemAdapter.MyViewHolder> () {

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
        val item = items[position]
        holder.itemTextView.text = item.name

        holder.itemTextView.setOnClickListener() {
            println("item name: " + item.name)
            // onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}