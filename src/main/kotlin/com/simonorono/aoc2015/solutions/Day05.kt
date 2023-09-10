package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day05 : Day(5) {
    private val badCombinations = arrayOf("ab", "cd", "pq", "xy")
    private val letterTwiceInARowRegex = """.*(.)\1.*""".toRegex()
    private val twoPairOfLettersTwiceWithoutOverlapping = """.*(..).*\1.*""".toRegex()
    private val sameLetterWithExactlyOneInBetween = """.*(.).\1.*""".toRegex()

    private val input = getInput().lines()

    private fun String.isNice1(): Boolean {
        if (badCombinations.any { this.contains(it) }) {
            return false
        }

        if (this.count { "aeiou".contains(it) } < 3) {
            return false
        }

        return this.matches(letterTwiceInARowRegex)
    }

    private fun String.isNice2(): Boolean {
        return this.matches(twoPairOfLettersTwiceWithoutOverlapping) && this.matches(sameLetterWithExactlyOneInBetween)
    }

    override fun part1(): String {
        return input.count { it.isNice1() }.toString()
    }

    override fun part2(): String {
        return input.count { it.isNice2() }.toString()
    }
}