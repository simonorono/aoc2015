package com.simonorono.advent_of_code.lib

abstract class Year(private val year: Int) {
    abstract val days: Array<Day>

    fun execute() {
        println("Year: $year:")

        days.withIndex().forEach {
            it.value.execute(it.index + 1)
        }
    }
}
