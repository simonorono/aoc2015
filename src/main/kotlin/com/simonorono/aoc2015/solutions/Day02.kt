package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day02 : Day(2) {
    data class Dimension(val l: Int, val w: Int, val h: Int) {
        private fun smallestSides(): List<Int> {
            val sides = arrayOf(l, w, h)
            sides.sort()
            return sides.take(2)
        }

        val totalSurfaceArea: Int
            get() = 2 * l * w + 2 * w * h + 2 * l * h

        val smallestSideArea: Int
            get() {
                val (a, b) = smallestSides()
                return a * b
            }

        val volume: Int
            get() = l * w * h

        val smallestPerimeter: Int
            get() {
                val (a, b) = smallestSides()
                return 2 * a + 2 * b
            }
    }

    private val input: Array<Dimension> = getInput().lines().map {
        val (l, w, h) = it.split('x').map { side ->
            side.toInt()
        }

        Dimension(l, w, h)
    }.toTypedArray()

    override fun part1(): String {
        return input
            .sumOf { it.smallestSideArea + it.totalSurfaceArea }
            .toString()
    }

    override fun part2(): String {
        return input
            .sumOf { it.volume + it.smallestPerimeter }
            .toString()
    }
}