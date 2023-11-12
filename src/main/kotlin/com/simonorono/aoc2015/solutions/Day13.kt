package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day13 : Day(13) {
    private val REGEX =
        """(.+) would (.+) (\d+) happiness units by sitting next to (.+).""".toRegex()

    private val happinessMap: HashMap<String, HashMap<String, Int>> =
        hashMapOf()

    init {

        for (line in getInput().lines()) {
            val match = REGEX.matchEntire(line)

            val (_, who, what, howMuch, neighbor) = match?.groupValues!!

            var mod = howMuch.toInt()
            if (what == "lose") {
                mod = -mod
            }

            if (happinessMap.contains(who)) {
                happinessMap[who]!![neighbor] = mod
            } else {
                happinessMap[who] = hashMapOf(neighbor to mod)
            }
        }
    }

    private fun getNeighbors(index: Int, max: Int): Pair<Int, Int> {
        return when (index) {
            0 -> 1 to max
            max -> 0 to max - 1
            else -> index - 1 to index + 1
        }
    }

    private fun calculateHappiness(list: Array<String>): Int {
        var happiness = 0

        for (i in list.indices) {
            val (leftIdx, rightIdx) = getNeighbors(i, list.size - 1)
            val guest = list[i]
            val left = list[leftIdx]
            val right = list[rightIdx]

            happiness += (happinessMap[guest]!![left]!! + happinessMap[guest]!![right]!!)
        }

        return happiness
    }

    override fun part1(): String {
        var max = 0

        for (permutation in calculatePermutations(happinessMap.keys.toTypedArray())) {
            val happiness = calculateHappiness(permutation)

            if (happiness > max) {
                max = happiness
            }
        }

        return max.toString()
    }

    override fun part2(): String {
        happinessMap["Me"] = hashMapOf()
        for (key in happinessMap.keys) {
            happinessMap["Me"]!![key] = 0
            happinessMap[key]!!["Me"] = 0
        }

        var max = 0

        for (permutation in calculatePermutations(happinessMap.keys.toTypedArray())) {
            val happiness = calculateHappiness(permutation)

            if (happiness > max) {
                max = happiness
            }
        }

        return max.toString()
    }
}