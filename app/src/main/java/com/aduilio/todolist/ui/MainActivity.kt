package com.aduilio.todolist.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aduilio.todolist.adapter.TaskListAdapter
import com.aduilio.todolist.databinding.ActivityMainBinding
import com.aduilio.todolist.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter

        initComponents()
        initListeners()
    }

    override fun onResume() {
        super.onResume()

        updateList()
    }

    private fun initComponents() {
        adapter.listenerEdit = {
            startActivity(
                Intent(
                    this,
                    AddTaskActivity::class.java
                ).putExtra(AddTaskActivity.TASK_ID, it.id)
            )
        }

        adapter.listenerDelete = {
            TaskDataSource.delete(it)
            updateList()
        }
    }

    private fun initListeners() {
        binding.fabNewTask.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    private fun updateList() {
        adapter.submitList(TaskDataSource.getList())
    }
}