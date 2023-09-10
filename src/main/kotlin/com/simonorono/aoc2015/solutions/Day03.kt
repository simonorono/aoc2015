package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day03 : Day(3) {
    data class Visited(val x: Int, val y: Int) {
        fun next(direction: Char): Visited {
            return when (direction) {
                '^' -> Visited(x, y + 1)
                'v' -> Visited(x, y - 1)
                '>' -> Visited(x + 1, y)
                '<' -> Visited(x - 1, y)
                else -> throw IllegalStateException()
            }
        }
    }

    private val input = getInput()

    override fun part1(): String {
        val visited = mutableListOf(Visited(0, 0))
        var current = Visited(0, 0)

        for (direction in input) {
            val next = current.next(direction)
            visited += next
            current = next
        }

        return visited.toSet().count().toString()
    }

    override fun part2(): String {
        val visited1 = mutableListOf(Visited(0, 0))
        val visited2 = mutableListOf<Visited>()

        var current1 = Visited(0, 0)
        var current2 = Visited(0, 0)

        for ((i, direction) in input.withIndex()) {
            if (i % 2 == 0) {
                val next = current1.next(direction)
                visited1 += next
                current1 = next
            } else {
                val next = current2.next(direction)
                visited2 += next
                current2 = next
            }
        }

        return visited1.toSet().union(visited2.toSet()).count().toString()
    }
}