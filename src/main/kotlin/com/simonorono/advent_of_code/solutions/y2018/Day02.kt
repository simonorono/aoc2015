package com.simonorono.advent_of_code.solutions.y2018

import com.simonorono.advent_of_code.lib.Day

object Day02 : Day(2018, 2) {
    val input = getInput().lines()

    override fun part1(): String {
        val counts = input.map { it.toCharArray().toList() }
            .map { it.groupingBy { c -> c }.eachCount() }
            .map { it.values }

        return (counts.count { it.contains(2) } * counts.count { it.contains(3) }).toString()
    }

    override fun part2(): String {
        for (i in input.indices) {
            for (j in input.indices) {
                if (i == j) {
                    continue
                }

                var diff = 0
                var diffIndex = 0

                for (k in input[i].indices) {
                    if (input[i][k] != input[j][k]) {
                        diff++
                        diffIndex = k
                    }
                }

                if (diff == 1) {
                    val chars = input[i].toMutableList()
                    chars.removeAt(diffIndex)
                    return chars.joinToString("")
                }
            }
        }

        return ""
    }
}