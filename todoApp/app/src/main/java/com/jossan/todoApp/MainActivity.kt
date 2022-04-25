package com.jossan.todoApp

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : Menu() {

    // private val itemList = ArrayList<String>()
    private val itemList = ArrayList<ListItem>()
    private lateinit var listItemAdapter: ListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        listItemAdapter = ListItemAdapter(itemList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listItemAdapter
        prepareItems()
    }

    private fun prepareItems() {
        itemList.add(ListItem("To do", arrayListOf(Item("Ett"), Item("Två"), Item("Tre"))))
        itemList.add(ListItem("Important", arrayListOf(Item("One"), Item("Two"), Item("Three"))))
        itemList.add(ListItem("Shopping", arrayListOf()))

        // listItemAdapter.notifyDataSetChanged()
        // itemAdapter.notifyItemChanged(0)
        listItemAdapter.onItemClick = {
            val listItemIndex = itemList.indexOf(it)

            println("itemList name: " + it.name)
            println("itemList index: " + listItemIndex)
            // println("itemList xxx: " + itemList)

            val intent = Intent(this, TodoActivity::class.java)
            intent.putExtra("listItem", it)
            intent.putExtra("index", listItemIndex)
            startActivity(intent)

            /* TODO - Send Data, Receive Data, Update List
            *   #1 - Send data to another Activity
            *   #2 - GET data (index and Name)
            *   #3 - Go back to mainActivity, Update ITEM at position index
            *   #4 - Implement empty array
            * */

        }
    }
}