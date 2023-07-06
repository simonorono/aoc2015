package com.simonorono.advent_of_code.lib

abstract class Year(val year: Int) {
    abstract val days: Array<Day>

    fun execute(day: Int? = null) {
        println("Year: $year:")

        days
            .filter { d ->
                day?.let { d.day == it } ?: true
            }
            .forEach {
                it.execute()
            }
    }
}
