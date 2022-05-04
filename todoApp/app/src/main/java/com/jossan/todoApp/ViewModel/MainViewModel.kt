package com.jossan.todoApp.ViewModel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var name: String = "Doris"

    fun addNemo() {
        name += ", Nemo"
    }
}