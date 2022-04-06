package com.jossan.todoApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : Menu() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}