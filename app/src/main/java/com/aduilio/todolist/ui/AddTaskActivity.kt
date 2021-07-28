package com.aduilio.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aduilio.todolist.databinding.ActivityAddTaskBinding
import com.aduilio.todolist.extension.format
import com.aduilio.todolist.extension.from
import com.aduilio.todolist.extension.text
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initListeners()
    }

    private fun initComponents() {

    }

    private fun initListeners() {
        binding.tilDate.editText?.setOnClickListener {
            dateClickListener()
        }

        binding.tilHour.editText?.setOnClickListener {
            timeClickListener()
        }

        binding.btSave.setOnClickListener {
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