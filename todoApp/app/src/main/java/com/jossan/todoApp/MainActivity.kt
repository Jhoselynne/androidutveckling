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
        listItemAdapter = ListItemAdapter(itemList)         // listItemAdapter = ListItemAdapter(mutableListOf())
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listItemAdapter
        prepareItems()
        updateItemList()
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

    private fun updateItemList() {
        // Get updated ITEM
        val updateItems = intent.getParcelableExtra<ListItem>("items")
        val listItemIndex = intent.getIntExtra("sameIndex", -1)
        //println("UPDATE ITEMS: " + updateItems?.items?.get(2)?.name)

        if (updateItems != null) {         // Check if NOT null
            for (i in 0 until updateItems.items?.size!!) {
                println("update items: " + updateItems.items?.get(i)?.name)
                //itemList.set(listItemIndex, updateItems.items?.get(i)?.name)
                itemList.set(listItemIndex, updateItems)
                //println("Debugging: " + itemList[listItemIndex].items?.get(i)?.name)
            }
            println("SAME INDEX: " + listItemIndex)
        }
    }
}