package com.jossan.todoApp.ViewModel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var name: String = "Benny"

    fun addNemo() {
        name += ", Nemo"
    }
}