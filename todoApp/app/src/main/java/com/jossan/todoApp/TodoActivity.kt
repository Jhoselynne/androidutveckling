package com.jossan.todoApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoActivity : AppCompatActivity() {

    // private val input = findViewById<EditText>(R.id.etAddTask)
    // private val inputTask = input.text

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        // Get data from MainActivity
        val listItem = intent.getParcelableExtra<ListItem>("listItem")
        val listItemIndex = intent.getIntExtra("index", -1)
        println("listItem: " + listItem?.name)
        println("listItemIndex: " + listItemIndex)

        if (listItem != null) {                                             // Check if NOT null
            for (i in 0 until listItem.items?.size!!)                       // For loop until Size
            // OBJECT = index (i)
                println("DEBUGGING #2 " + listItem.items?.get(i)?.name)     // GET index.name
            // println("listItem.Items: " + listItem?.items)

        }

        /*
        input.setOnClickListener() {
            if (inputTask.isNotEmpty()) {
                listItem?.items?.add(Item(inputTask.toString()))      // Kod för att lägga till ett item via input
                inputTask.clear()
            }
        }
        */

        if (listItem != null) {
            val textView: TextView = findViewById(R.id.todoTV)

            textView.text = listItem.name
            // prepareItems(listItem.items)

            // imageView.setImageResource(item.image)       // använd kod för olika ikoner
            listItem.items?.add(Item("Six"))            // Add new item to listItem
        }

        val recyclerView: RecyclerView = findViewById(R.id.rv_todo)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ItemAdapter(listItem!!.items!!)


        val backBtn = findViewById<ImageButton>(R.id.imageButton)

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            if (listItem != null) {                                                 // Check if NOT null
                for (i in 0 until listItem.items?.size!!) {                         // For loop until Size
                    // OBJECT = index (i)
                    println("DEBUGGING #3 " + listItem.items?.get(i)?.name)         // Get index.name
                    // println("TESTING: " + listItem.items)
                    //intent.putExtra("items", listItem.items?.get(i)?.name.toString())
                }

                // println("Samma Index som tidigare: " + listItemIndex)        // Ta bort
                intent.putExtra("sameIndex", listItemIndex)
                intent.putExtra("items", listItem)
            }

            startActivity(intent)
        }
    }
}
