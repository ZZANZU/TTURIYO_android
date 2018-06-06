package io.github.tturiyo.base.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateTimeFormat(format: String): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    dateFormat.calendar = calendar
    return dateFormat.format(calendar.time)
}