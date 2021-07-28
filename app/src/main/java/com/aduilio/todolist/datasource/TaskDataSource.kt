package com.aduilio.todolist.datasource

import com.aduilio.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list

    fun insert(task: Task) {
        list.add(task.copy(id = list.size + 1))
    }
}