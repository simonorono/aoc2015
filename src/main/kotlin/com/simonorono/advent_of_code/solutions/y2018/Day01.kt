package com.simonorono.advent_of_code.solutions.y2018

import com.simonorono.advent_of_code.lib.Day

object Day01 : Day(2018, 1) {
    private val input: List<Int> = getInput().lines().map {
        it.substring(1).toInt() * if (it[0] == '-') -1 else 1
    }

    override fun part1(): String {
        var frequency = 0

        for (mod in input) {
            frequency += mod
        }

        return frequency.toString()
    }

    override fun part2(): String {
        var frequency = 0
        val seen = hashMapOf(0 to true)

        while (true) {
            for (mod in input) {
                frequency += mod

                if (seen.contains(frequency)) {
                    return frequency.toString()
                }

                seen[frequency] = true
            }
        }
    }
}