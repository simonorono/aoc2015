package com.simonorono.advent_of_code.solutions

import com.simonorono.advent_of_code.lib.Day

object Year {
    private val days: Array<Day>
        get() = arrayOf(Day01, Day02, Day03, Day04, Day05, Day06)

    fun execute(day: Int? = null) {
        days
            .filter { d ->
                day?.let { d.day == it } ?: true
            }
            .forEach {
                it.execute()
            }
    }
}
