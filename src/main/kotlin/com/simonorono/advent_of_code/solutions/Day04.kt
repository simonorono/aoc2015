package com.simonorono.advent_of_code.solutions

import com.simonorono.advent_of_code.lib.Day
import java.security.MessageDigest

object Day04 : Day(4) {
    private val input = getInput()
    private val md5 = MessageDigest.getInstance("MD5")

    private fun String.getMD5(): ByteArray {
        return md5.digest(this.toByteArray())
    }

    private inline val Int.b get() = this.toByte()

    private fun getMD5UntilCondition(condition: (ByteArray) -> Boolean): Int {
        var current = 1

        while (true) {
            if (condition("$input$current".getMD5())) {
                return current
            }
            current++
        }
    }

    override fun part1(): String {
        return getMD5UntilCondition {
            it[0] == 0.b && it[1] == 0.b && (it[2].toInt().shr(4).and(0xF)).b == 0.b
        }.toString()
    }

    override fun part2(): String {
        return getMD5UntilCondition {
            it[0] == 0.b && it[1] == 0.b && it[2] == 0.b
        }.toString()
    }
}