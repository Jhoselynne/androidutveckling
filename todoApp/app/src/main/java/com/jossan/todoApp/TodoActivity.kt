package com.jossan.todoApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoActivity : AppCompatActivity() {

    // DEBUGGING
    // private var items = ArrayList<Item>()
    // private lateinit var itemAdapter: ItemAdapter
    // DEBUGGING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val listItem = intent.getParcelableExtra<ListItem>("listItem")
        val listItemIndex = intent.getIntExtra("index", -1)
        println("listItem: " + listItem?.name)
        println("listItemIndex: " + listItemIndex)

        if(listItem != null) {
            val textView : TextView = findViewById(R.id.todoTV)

            textView.text = listItem.name
            println(listItem.items)
            // prepareItems(listItem.items)

            // använd kod under för olika ikoner
            // imageView.setImageResource(item.image)
        }

        // DEBUGGING
        val recyclerView: RecyclerView = findViewById(R.id.rv_todo)
        // itemAdapter = ItemAdapter(items)
        //this.items = listItem!!.items!!
        //itemAdapter = ItemAdapter(this.items)
        //itemAdapter = ItemAdapter(listItem!!.items!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = itemAdapter
        recyclerView.adapter = ItemAdapter(listItem!!.items!!)

        // DEBUGGING

        }

    // DEBUGGING
   /*  private fun prepareItems(items: ArrayList<Item>?) {

        if (items != null) {
            for (item in items) {
                this.items.add(item)
            }
        }

    */
        // DEBUGGING
        /* for (i in 0..20) {
            itemList.add(ListItem("To do"))
            itemList.add(ListItem("Important"))
            itemList.add(ListItem("Shopping"))

            itemAdapter.notifyDataSetChanged()
        }

    }*/
    // DEBUGGING

}
