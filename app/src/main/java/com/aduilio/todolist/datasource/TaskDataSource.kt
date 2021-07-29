package com.aduilio.todolist.datasource

import com.aduilio.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insert(task: Task) {
        if (task.id == 0) {
            list.add(task.copy(id = list.size + 1))
        } else {
            list.remove(task)
            list.add(task)
        }
    }

    fun findById(taskId: Int): Task? {
        return list.find { task ->
            task.id == taskId
        }
    }

    fun delete(task: Task) {
        list.remove(task)
    }
}