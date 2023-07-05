package com.simonorono.advent_of_code.solutions.y2022

import com.simonorono.advent_of_code.lib.Day

object Day01 : Day(2022, 1) {
    private val input = mutableListOf<Int>()

    init {
        var current = 0

        getInput().lines().forEach {
            if (it == "") {
                input += current
                current = 0
            } else {
                current += it.toInt()
            }
        }

        input.sortDescending()
    }

    override fun part1(): String {
        return input[0].toString()
    }

    override fun part2(): String {
        return input.take(3).sum().toString()
    }
}