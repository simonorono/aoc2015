package com.simonorono.advent_of_code.solutions.y2022

import com.simonorono.advent_of_code.lib.Day

object Day03: Day(2022, 3) {
    private val Char.value: Int
        get() = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(this) + 1

    private val input = this.getInput().lines()

    override fun part1(): String {
        return this.input
            .map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
            .map { (a, b) -> a.toSet().intersect(b.toSet()) }
            .sumOf { it.first().value }
            .toString()
    }

    override fun part2(): String {
        return this.input.chunked(3)
            .map { (a, b, c) -> a.toSet().intersect(b.toSet()).intersect(c.toSet()) }
            .sumOf { it.first().value }
            .toString()
    }
}