package ua.alegator1209.data.mappers

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:MM:SS'Z'", Locale.US)

fun String.toDate(): Date = dateFormat.parse(this) ?: error("cannot parse $this")
fun Date.toDateString(): String = dateFormat.format(this)
