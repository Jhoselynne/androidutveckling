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

    private var itemList = ArrayList<ListItem>()
    private lateinit var listItemAdapter: ListItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("MainActivity - onCreate()")

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

        // Recycler View
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        listItemAdapter = ListItemAdapter(itemList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listItemAdapter
        prepareItems()
    }

    private fun prepareItems() {
        itemList.add(ListItem("To do", arrayListOf(Item("Buy Coffee"), Item("Bean"))))
        itemList.add(ListItem("Shopping", arrayListOf()))

        listItemAdapter.onItemClick = {
            val listItemIndex = itemList.indexOf(it)

            // Intent sends data to TodoActivity
            val intent = Intent(this, TodoActivity::class.java)
            intent.putExtra("listItem", it)
            intent.putExtra("index", listItemIndex)
            startActivityForResult(intent, 1)
        }
    }

    // Get result(data) from TodoActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val index = data?.getIntExtra("sameIndex", -1)
            var index2 = -1
            if (index != null) {
                index2 = index
            }
            // println("MainActivity - onActivityResult - index = " + index)
            val itemList2 = data!!.getParcelableExtra<ListItem>("items")
            // println("MainActivity - onActivityResult - itemList.items.size = " + itemList2?.items?.size)
            if (itemList2 != null) {
                itemList.set(index2, itemList2)                 // Replace ListItem
                listItemAdapter.notifyDataSetChanged()          // Show updated list
            }
        }
    }
    /*
    override fun onStart() {
        super.onStart()
        println("MainActivity - onStart()")
    }

    override fun onResume() {
        super.onResume()
        println("MainActivity - onResume()")
    }

    override fun onRestart() {
        super.onRestart()
        println("MainActivity - onRestart()")
    }

    override fun onPause() {
        super.onPause()
        println("MainActivity - onPause()")
    }

    override fun onStop() {
        super.onStop()
        println("MainActivity - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MainActivity - onDestroy()")
    }
    */
}