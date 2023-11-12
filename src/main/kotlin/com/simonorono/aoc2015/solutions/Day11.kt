package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day11 : Day(11) {
    private val REPEAT_CHARS_REGEX = """.*?(.)\1.*?(.)\2.*?""".toRegex()

    private fun isValidPassword(pass: String): Boolean {
        if (pass.toSet().intersect(setOf('o', 'i', 'l')).isNotEmpty()) {
            return false
        }

        var sawIncrement = false

        for (i in 0..<(pass.length - 3)) {
            if (pass[i + 2] == (pass[i + 1] + 1) && pass[i + 1] == (pass[i] + 1)) {
                sawIncrement = true
                break
            }
        }

        if (!sawIncrement) {
            return false
        }

        return pass.matches(REPEAT_CHARS_REGEX)
    }

    private fun nextPass(pass: String): String {
        var index = pass.length - 1
        val newPass = StringBuilder(pass)

        while (index >= 0) {
            if (newPass[index] == 'z') {
                newPass[index] = 'a'
                index -= 1
            } else {
                newPass[index] = newPass[index] + 1
                break;
            }
        }

        return newPass.toString()
    }

    override fun part1(): String {
        var pass = getInput()

        while (!isValidPassword(pass)) {
            pass = nextPass(pass)
        }

        return pass
    }

    override fun part2(): String {
        // starting with the answer from part1
        var pass = nextPass(part1())

        while (!isValidPassword(pass)) {
            pass = nextPass(pass)
        }

        return pass
    }
}