package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Year {
    private val days: Array<Day>
        get() = arrayOf(
            Day01,
            Day02,
            Day03,
            Day04,
            Day05,
            Day06,
            Day07,
            Day08,
            Day09,
            Day10,
            Day11,
            Day12,
            Day13,
        )

    fun execute(day: Int? = null) {
        days.filter { d ->
            day?.let { d.day == it } ?: true
        }.forEach {
            it.execute()
        }
    }
}
