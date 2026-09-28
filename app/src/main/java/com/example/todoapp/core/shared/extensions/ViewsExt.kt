package com.example.todoapp.core.shared.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.show(){
isVisible = true
}

fun View.hide(){
    isVisible = false
}