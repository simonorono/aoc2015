package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day
import kotlin.math.abs

object Day08 : Day(8) {
    private val input = getInput().lines()

    private val codeCount = input.sumOf { it.length }

    override fun part1(): String {
        val memoryCount = input.map {
            it.trim('"') // remove quotes
                // changing them to '0' just so they be a single char
                .replace("""\\[xX][0-9a-fA-F]{2}""".toRegex(), "0")
                .replace("\\\"", "0")
                .replace("\\\\", "0")
        }.sumOf { it.length }

        return abs(codeCount - memoryCount).toString()
    }

    override fun part2(): String {
        val newRepresentationCount = input.map {
            val new = it.replace("\\", "\\\\")
                .replace("\"", "\\\"")

            "\"${new}\""
        }.sumOf { it.length }

        return abs(codeCount - newRepresentationCount).toString()
    }
}