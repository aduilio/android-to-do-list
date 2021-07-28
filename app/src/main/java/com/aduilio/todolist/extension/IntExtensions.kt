package com.aduilio.todolist.extension

fun Int.format(): String {
    return if (this < 10) {
        "0$this"
    } else {
        "$this"
    }
}