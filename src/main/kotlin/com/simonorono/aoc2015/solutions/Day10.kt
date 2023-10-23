package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day
import java.util.*

object Day10 : Day(10) {
    private val input = getInput().trim()

    private fun lookAndSay(str: String): String {
        /*
        using this collection of pairs to keep track of the counts and using it
        at the end to form the string is way faster than to continually build
        the string during the algorithm run.
         */
        val collection = LinkedList<Pair<Char, Int>>()

        var currentChar = str[0]
        var currentCount = 0
        var looking = 0

        while (looking < str.length) {
            if (str[looking] != currentChar) {
                collection.add(currentChar to currentCount)
                currentCount = 0
            }

            currentChar = str[looking]
            currentCount++

            looking++
        }

        collection.add(currentChar to currentCount)

        return collection.joinToString("") { "${it.second}${it.first}" }
    }

    override fun part1(): String {
        var current = input

        repeat(40) {
            current = lookAndSay(current)
        }

        return current.length.toString()
    }

    override fun part2(): String {
        var current = input

        repeat(50) {
            current = lookAndSay(current)
        }

        return current.length.toString()
    }
}