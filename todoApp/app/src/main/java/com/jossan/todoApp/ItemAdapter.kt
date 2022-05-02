package com.jossan.todoApp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

internal class ItemAdapter (private var items: ArrayList<Item>)
    : RecyclerView.Adapter<ItemAdapter.MyViewHolder> () {

    var itemsFiltered = ArrayList<Item>()
    init {
        itemsFiltered = items
    }

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
        val item = itemsFiltered[position]
        holder.itemTextView.text = item.name

        holder.itemTextView.setOnClickListener() {
            println("item name: " + item.name)
        }
    }

    fun deleteItem(view : RecyclerView.ViewHolder) {
        val filteredIndex = view.absoluteAdapterPosition
        val item = itemsFiltered[filteredIndex]
        val index = items.indexOf(item)

        println("deletedItem - filteredIndex: " + filteredIndex)
        println("deletedItem - item.id: " + item.id)
        println("deletedItem - index: " + index)

        items.removeAt(index)
        itemsFiltered.removeAt(filteredIndex)

        notifyItemRemoved(filteredIndex)
    }
    /* fun deleteItem(view: RecyclerView.ViewHolder) {
        // println("absoluteAdapter: " + view.absoluteAdapterPosition)
        // items.removeAt(view.absoluteAdapterPosition)
        // notifyItemRemoved(view.absoluteAdapterPosition)
    }   */



    /* fun addItem(i : Int, item : Item) {
        items.add(i, item)
        notifyDataSetChanged()
    } */

    override fun getItemCount(): Int {
        return itemsFiltered.size
    }

    fun getFilter() : Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsFiltered = items
                } else {
                    val resultList = ArrayList<Item>()
                    for (row in items) {
                        if (row.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            println("row: " + row.id)
                            resultList.add(row)
                        }
                    }
                    itemsFiltered = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemsFiltered
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null) {
                    itemsFiltered = results.values as ArrayList<Item>
                    notifyDataSetChanged()
                }
            }
        }
    }
}