package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day06 : Day(6) {
    enum class Op(val offset: Int) {
        TurnOn(8),
        TurnOff(9),
        Toggle(7);

        companion object {
            fun getOp(line: String): Op {
                if (line.startsWith("turn on")) {
                    return TurnOn
                }

                if (line.startsWith("turn off")) {
                    return TurnOff
                }

                if (line.startsWith("toggle")) {
                    return Toggle
                }

                throw IllegalStateException()
            }
        }
    }

    data class Point(val x: Int, val y: Int)
    data class PointRange(val start: Point, val end: Point)

    private val REGEX = """(\d+),(\d+) through (\d+),(\d+)""".toRegex()

    private fun getOpRange(line: String): Pair<Op, PointRange> {
        val op = Op.getOp(line)

        val match = REGEX.matchEntire(line.substring(op.offset))!!

        val x1 = match.groupValues[1].toInt()
        val y1 = match.groupValues[2].toInt()
        val x2 = match.groupValues[3].toInt()
        val y2 = match.groupValues[4].toInt()

        return op to PointRange(Point(x1, y1), Point(x2, y2))
    }

    private val input = getInput().lines().map { getOpRange(it) }

    override fun part1(): String {
        val lights = Array(1000) { Array(1000) { false } }

        for ((op, range) in input) {
            for (i in range.start.x..range.end.x) {
                for (j in range.start.y..range.end.y) {
                    lights[i][j] = when (op) {
                        Op.TurnOn -> true
                        Op.TurnOff -> false
                        Op.Toggle -> !lights[i][j]
                    }
                }
            }
        }

        var lightsOn = 0

        for (row in lights) {
            for (light in row) {
                if (light) {
                    lightsOn++
                }
            }
        }

        return lightsOn.toString()
    }

    override fun part2(): String {
        val lights = Array(1000) { Array(1000) { 0u } }

        for ((op, range) in input) {
            for (i in range.start.x..range.end.x) {
                for (j in range.start.y..range.end.y) {
                    when (op) {
                        Op.TurnOn -> lights[i][j] += 1u

                        Op.TurnOff -> {
                            if (lights[i][j] == 0u) {
                                continue
                            }

                            lights[i][j] -= 1u
                        }

                        Op.Toggle -> lights[i][j] += 2u
                    }
                }
            }
        }

        var totalBrightness = 0u

        for (row in lights) {
            for (light in row) {
                totalBrightness += light
            }
        }

        return totalBrightness.toString()
    }
}