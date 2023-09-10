package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day01 : Day(1) {
    private val input = getInput()

    override fun part1(): String {
        var position = 0

        for (c in input) {
            when (c) {
                '(' -> position++
                ')' -> position--
                else -> {}
            }
        }

        return position.toString()
    }

    override fun part2(): String {
        var position = 0

        for (c in input.withIndex()) {
            when (c.value) {
                '(' -> position++
                ')' -> position--
                else -> {}
            }

            if (position == -1) {
                return "${c.index + 1}"
            }
        }

        throw IllegalStateException()
    }
}