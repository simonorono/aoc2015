package com.simonorono.advent_of_code.solutions.y2015

import com.simonorono.advent_of_code.lib.Day
import java.security.MessageDigest

object Day04 : Day(2015, 4) {
    private val input = getInput()
    private val md5 = MessageDigest.getInstance("MD5")

    private fun String.getMD5(): ByteArray {
        return md5.digest(this.toByteArray())
    }

    private fun getMD5UntilCondition(condition: (ByteArray) -> Boolean): Int {
        var current = 282748

        while (true) {
            if (condition("$input$current".getMD5())) {
                return current
            }
            current++
        }
    }

    override fun part1(): String {
        return getMD5UntilCondition {
            it[0] == 0.toByte() &&
                    it[1] == 0.toByte() &&
                    (it[2].toInt().shl(4).and(0xF)).toByte() == 0.toByte()
        }.toString()
    }

    override fun part2(): String {
        return getMD5UntilCondition {
            it[0] == 0.toByte() && it[1] == 0.toByte() && it[2] == 0.toByte()
        }.toString()
    }
}