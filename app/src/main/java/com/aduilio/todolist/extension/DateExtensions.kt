package com.aduilio.todolist.extension

import android.content.Context
import android.text.format.DateFormat
import java.util.*

fun Date.from(time: Long): Date {
    val offsetTime = TimeZone.getDefault().getOffset(Date().time) * -1
    return Date(time + offsetTime)
}

fun Date.format(context: Context): String {
    val dateFormat = DateFormat.getDateFormat(context)
    return dateFormat.format(this)
}