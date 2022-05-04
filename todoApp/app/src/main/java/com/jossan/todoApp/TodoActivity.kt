package com.jossan.todoApp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoActivity : AppCompatActivity() {

    // private lateinit var viewModel: TodoViewModelViewModel
    private lateinit var itemAdapter : ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        println("TodActivity - onCreate()")

        // Connection to ViewModel + Instantiating
        // viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        // ID's
        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.search_bar)

        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                itemAdapter.getFilter().filter(newText)
                return false
            }
        })

        // Get data from MainActivity
        val listItem = intent.getParcelableExtra<ListItem>("listItem")
        val listItemIndex = intent.getIntExtra("index", -1)
        println("TodoActivity - listItem.name: " + listItem?.name)
        println("TodoActivity - listItemIndex: " + listItemIndex)

        /*
        if (listItem != null) {                                             // Check if NOT null
            for (i in 0 until listItem.items?.size!!)                       // For loop until Size
            // OBJECT = index (i)
                println("DEBUGGING #2 " + listItem.items?.get(i)?.name)     // GET index.name
            // println("listItem.Items: " + listItem?.items)

        }
        */

        if (listItem != null) {
            val textView: TextView = findViewById(R.id.todoTV)

            textView.text = listItem.name
            // imageView.setImageResource(item.image)       // Use code for different icons
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val input = findViewById<EditText>(R.id.etAddTask)
        val inputTask = input.text

        // // New items adds to listItem
        btnAdd.setOnClickListener() {
            if (inputTask.isNotEmpty()) {
                // viewModel.items?.add(Item(inputTask.toString()))
                listItem?.items?.add(Item(inputTask.toString()))        // Code to add an ITEM through input
                inputTask.clear()
                itemAdapter.notifyItemInserted(itemAdapter.itemCount)
            }
        }

        // Recycler View
        val recyclerView: RecyclerView = findViewById(R.id.rv_todo)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        itemAdapter = ItemAdapter(listItem!!.items!!)
        recyclerView.adapter = itemAdapter


        // Gesture through swipe
        val swipeGesture = object : SwipeGesture(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction) {
                    ItemTouchHelper.LEFT -> {
                        itemAdapter.deleteItem(viewHolder)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(recyclerView)


        val backBtn = findViewById<ImageButton>(R.id.imageButton)

        // Send updated data back to MainActivity
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            if (listItem != null) {
                intent.putExtra("sameIndex", listItemIndex)
                intent.putExtra("items", listItem)
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
    /*
    override fun onStart() {
        super.onStart()
        println("TodoActivity - onStart()")
    }

    override fun onResume() {
        super.onResume()
        println("TodoActivity - onResume()")
    }

    override fun onRestart() {
        super.onRestart()
        println("TodoActivity - onRestart()")
    }

    override fun onPause() {
        super.onPause()
        println("TodoActivity - onPause()")
    }

    override fun onStop() {
        super.onStop()
        println("TodoActivity - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("TodoActivity - onDestroy()")
    }   */
}
