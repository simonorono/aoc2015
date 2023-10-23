package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

private typealias Graph = HashMap<String, HashMap<String, Int>>

object Day09 : Day(9) {
    private val routes: Graph = hashMapOf()
    private val distances: Array<Int>

    init {
        getInput().lines().forEach {
            val (route, weightStr) = it.split(" = ")
            val (origin, destination) = route.split(" to ")
            val weight = weightStr.toInt()

            if (routes.contains(origin)) {
                routes[origin]!![destination] = weight
            } else {
                routes[origin] = hashMapOf(destination to weight)
            }

            if (routes.contains(destination)) {
                routes[destination]!![origin] = weight
            } else {
                routes[destination] = hashMapOf(origin to weight)
            }
        }

        distances = calculatePermutations(routes.keys.toTypedArray())
            .map { getRouteDistance(it) }
            .toTypedArray()
    }

    private fun calculatePermutations(elems: Array<String>): Array<Array<String>> {
        val results = arrayListOf(elems.clone())
        val indices = Array(elems.size) { 0 }

        var i = 0

        while (i < elems.size) {
            if (indices[i] < i) {

                // swap
                val indexToSwap = if (i % 2 == 0) 0 else indices[i]
                val tmp = elems[indexToSwap]
                elems[indexToSwap] = elems[i]
                elems[i] = tmp

                results += elems.clone()

                indices[i]++
                i = 0
            } else {
                indices[i] = 0
                i++
            }
        }

        return results.toTypedArray()
    }

    private fun getRouteDistance(elems: Array<String>): Int {
        var result = 0

        for (i in 0..elems.size - 2) {
            val from = elems[i]
            val to = elems[i + 1]
            val routesFrom = routes[from]!!

            if (!routesFrom.contains(to)) {
                return Int.MAX_VALUE
            }

            result += routesFrom[to]!!
        }

        return result
    }

    override fun part1(): String {
        return distances.min().toString()
    }

    override fun part2(): String {
        return distances.max().toString()
    }
}