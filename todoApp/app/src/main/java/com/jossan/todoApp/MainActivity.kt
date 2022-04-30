package com.jossan.todoApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jossan.todoApp.ViewModel.MainViewModel

class MainActivity : Menu() {

    private lateinit var viewModel: MainViewModel

    private val itemList = ArrayList<ListItem>()
    private lateinit var listItemAdapter: ListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Id's for ViewModel
        val tvViewModel = findViewById<TextView>(R.id.tvViewModel)
        val changeViewModel = findViewById<Button>(R.id.btnViewModel)

        // Connection to ViewModel + Instantiating
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        tvViewModel.text = viewModel.name       // Init value

        changeViewModel.setOnClickListener() {
            viewModel.addNemo()

            tvViewModel.text = viewModel.name       // Set value
        }

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
        itemList.add(ListItem("Groceries", arrayListOf(Item("Coffee"))))
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