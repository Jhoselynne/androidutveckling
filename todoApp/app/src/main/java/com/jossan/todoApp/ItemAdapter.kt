package com.jossan.todoApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class ItemAdapter(private var itemsList: ArrayList<String>)
    : RecyclerView.Adapter<ItemAdapter.MyViewHolder> () {

    // Filter list for search
    /* var itemFilterList = ArrayList<String>()

    init {
        itemFilterList = itemsList
    } */

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
        val item = itemsList[position]
        holder.itemTextView.text = item

        holder.itemTextView.setOnClickListener() {
            println(item)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


}