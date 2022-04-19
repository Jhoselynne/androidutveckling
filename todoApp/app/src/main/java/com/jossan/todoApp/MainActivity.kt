package com.jossan.todoApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : Menu() {

    private val itemList = ArrayList<String>()
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        itemAdapter = ItemAdapter(itemList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = itemAdapter
        prepareItems()
    }

    private fun prepareItems() {
        itemList.add("Todo")
        itemList.add("Important")
        itemList.add("Shopping")
        itemAdapter.notifyDataSetChanged()
        // customAdapter.notifyItemChanged(0)
    }
}