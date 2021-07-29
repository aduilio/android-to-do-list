package com.aduilio.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aduilio.todolist.databinding.ActivityAddTaskBinding
import com.aduilio.todolist.datasource.TaskDataSource
import com.aduilio.todolist.extension.format
import com.aduilio.todolist.extension.from
import com.aduilio.todolist.extension.text
import com.aduilio.todolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AddTaskActivity : AppCompatActivity() {

    companion object {
        const val TASK_ID = "TASK_ID"
    }

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(TASK_ID)) {
            val taskId = intent.getIntExtra(TASK_ID, 0)
            val task = TaskDataSource.findById(taskId)
            task?.let {
                binding.etTask.setText(it.title)
                binding.etDescription.setText(it.description)
                binding.etDate.setText(it.date)
                binding.etHour.setText(it.hour)
            }
        }

        initListeners()
    }

    private fun initListeners() {
        binding.tilDate.editText?.setOnClickListener {
            dateClickListener()
        }

        binding.tilHour.editText?.setOnClickListener {
            timeClickListener()
        }

        binding.btSave.setOnClickListener {
            TaskDataSource.insert(
                Task(
                    title = binding.tilTitle.text,
                    description = binding.tilDescription.text,
                    date = binding.tilDate.text,
                    hour = binding.tilHour.text,
                    id = intent.getIntExtra(TASK_ID, 0)
                )
            )
            finish()
        }

        binding.btCancel.setOnClickListener {
            finish()
        }
    }

    private fun dateClickListener() {
        val datePicker = MaterialDatePicker.Builder
            .datePicker()
            .build()
        datePicker.addOnPositiveButtonClickListener { date ->
            binding.tilDate.text = from(date).format(this)
        }
        datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
    }

    private fun timeClickListener() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        timePicker.addOnPositiveButtonClickListener {
            binding.tilHour.text = "${timePicker.hour.format()}:${timePicker.minute.format()}"
        }
        timePicker.show(supportFragmentManager, "TIME_PICKER_TAG")
    }
}