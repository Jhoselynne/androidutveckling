package com.jossan.todoApp

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class Menu: AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.todo_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.todo -> {
                todo()
                true
            }
            R.id.shoppingList -> {
                shopping()
                true
            }

            else -> super.onOptionsItemSelected(item)   // = Return false
        }
    }

    private fun todo() {
        Toast.makeText(this, "Write some task", Toast.LENGTH_LONG).show()
    }

    private fun shopping() {
        Toast.makeText(this, "Add some groceries", Toast.LENGTH_LONG).show()
    }
}