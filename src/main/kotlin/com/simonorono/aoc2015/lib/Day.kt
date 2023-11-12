package com.simonorono.aoc2015.lib

abstract class Day(val day: Int) {
    fun getInput(): String {
        return javaClass
            .classLoader
            .getResource("input/day$day.txt")!!
            .readText()
    }

    abstract fun part1(): String

    abstract fun part2(): String

    fun execute() {
        println("Day $day:")
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
        println()
    }

    fun calculatePermutations(elems: Array<String>): Array<Array<String>> {
        val results = arrayListOf(elems.clone())
        val indices = Array(elems.size) { 0 }

        var i = 0

        while (i < elems.size) {
            if (indices[i] < i) {

                // swap
                val indexToSwap = if (i % 2 == 0) 0 else indices[i]
                val tmp = elems[indexToSwap]
                elems[indexToSwap] = elems[i]
                elems[i] = tmp

                results += elems.clone()

                indices[i]++
                i = 0
            } else {
                indices[i] = 0
                i++
            }
        }

        return results.toTypedArray()
    }
}
