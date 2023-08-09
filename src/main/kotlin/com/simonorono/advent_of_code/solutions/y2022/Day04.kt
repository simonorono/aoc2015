package com.simonorono.advent_of_code.solutions.y2022

import com.simonorono.advent_of_code.lib.Day

object Day04: Day(2022, 4) {
    val input = getInput().lines()
        .map { it.split(',') }
        .map { it.map { b -> b.split('-') } }
        .map { (a, b) ->
            val (p1, p2) = a.map { it.toInt() }
            val (p3, p4) = b.map { it.toInt() }

            (p1..p2) to (p3..p4)
        }

    private fun IntRange.fullyContains(other: IntRange): Boolean {
        return this.contains(other.first) && this.contains(other.last)
    }

    private fun IntRange.overlaps(other: IntRange): Boolean {
        return this.contains(other.first) || this.contains(other.last)
    }

    override fun part1(): String {
        return this.input.count { (a, b) ->
            a.fullyContains(b) || b.fullyContains(a)
        }.toString()
    }

    override fun part2(): String {
        return this.input.count { (a, b) ->
            a.overlaps(b) || b.overlaps(a)
        }.toString()
    }
}