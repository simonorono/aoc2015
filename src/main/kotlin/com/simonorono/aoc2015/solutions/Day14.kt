package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day
import kotlin.math.max
import kotlin.math.min

object Day14 : Day(14) {
    private val REGEX =
        """(.+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""".toRegex()

    private data class Reindeer(
        val name: String,
        val speed: Int,
        val runningTime: Int,
        val restingTime: Int,
    ) {
        fun run(time: Int): Int {
            var elapsed = 0
            var totalRan = 0

            while (elapsed < time) {
                val maxRun = max(0, time - elapsed)

                val running = min(runningTime, maxRun)
                totalRan += running * speed
                elapsed += running

                val maxRest = max(0, time - elapsed)
                elapsed += min(restingTime, maxRest)
            }

            return totalRan
        }
    }

    private val reindeer = mutableListOf<Reindeer>()

    init {
        for (line in getInput().lines()) {
            val match = REGEX.matchEntire(line)
            val (_, name, speed, running, resting) = match!!.groupValues

            reindeer += Reindeer(
                name, speed.toInt(), running.toInt(), resting.toInt()
            )
        }
    }

    override fun part1(): String {
        var max = 0

        for (reindeer in reindeer) {
            val run = reindeer.run(2503)

            if (run > max) {
                max = run
            }
        }

        return max.toString()
    }

    override fun part2(): String {
        val running = reindeer.associateWith { true }.toMutableMap()
        val nextState = reindeer.associateWith { it.runningTime }.toMutableMap()
        val points = reindeer.associateWith { 0 }.toMutableMap()
        val totalRun = reindeer.associateWith { 0 }.toMutableMap()

        repeat(2503) {
            for (reindeer in reindeer) {
                if (running[reindeer]!!) {
                    totalRun[reindeer] = totalRun[reindeer]!! + reindeer.speed
                }

                nextState[reindeer] = nextState[reindeer]!! - 1

                if (nextState[reindeer]!! == 0) {
                    running[reindeer] = !running[reindeer]!!
                    nextState[reindeer] =
                        if (running[reindeer]!!) reindeer.runningTime else reindeer.restingTime
                }
            }

            val lead = reindeer.maxBy { totalRun[it]!! }
            points[lead] = points[lead]!! + 1
        }

        return points.values.max().toString()
    }
}