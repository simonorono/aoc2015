package com.simonorono.advent_of_code.lib

abstract class Day {
    abstract fun part1(): String

    abstract fun part2(): String

    fun execute(day: Int) {
        println("Day $day:")
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
        println()
    }
}
