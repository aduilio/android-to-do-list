package com.aduilio.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aduilio.todolist.databinding.ActivityAddTaskBinding
import com.aduilio.todolist.extension.format
import com.aduilio.todolist.extension.from
import com.aduilio.todolist.extension.text
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

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
            val datePicker = MaterialDatePicker.Builder
                .datePicker()
                .build()
            datePicker.addOnPositiveButtonClickListener { date ->
                binding.tilDate.text = Date().from(date).format(this)
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }
    }
}