package com.example.charigochi.utils

import java.util.Calendar
import java.util.Date

fun twoDatesIsSameDayOfYear(d1: Date, d2: Date): Boolean {
    val cal1: Calendar = Calendar.getInstance()
    val cal2: Calendar = Calendar.getInstance()
    cal1.setTime(d1)
    cal2.setTime(d2)
    return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
}

infix fun Date.isYesterdayFor(date: Date): Boolean {
    val cal1: Calendar = Calendar.getInstance()
    val cal2: Calendar = Calendar.getInstance()
    cal1.setTime(this)
    cal2.setTime(date)

    val day1 = cal1.get(Calendar.DAY_OF_YEAR)
    val day2 = cal2.get(Calendar.DAY_OF_YEAR)

    val year1 = cal1.get(Calendar.YEAR)
    val year2 = cal2.get(Calendar.YEAR)

    return if (day2 == 1)
        day1 >= 365 && year2 - 1 == year1
    else day1 == day2 - 1 && year1 == year2

}