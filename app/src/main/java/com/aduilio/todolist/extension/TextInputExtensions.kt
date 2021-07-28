package com.aduilio.todolist.extension

import com.google.android.material.textfield.TextInputLayout

var TextInputLayout.text: String
    get() = editText?.text?.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }