package com.aduilio.todolist.model

data class Task(
    val title: String,
    val description: String,
    val date: String,
    val id: Int = 0
)
