package com.simonorono.advent_of_code

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.restrictTo
import com.simonorono.advent_of_code.solutions.y2015.Y2015
import com.simonorono.advent_of_code.solutions.y2018.Y2018
import com.simonorono.advent_of_code.solutions.y2022.Y2022

class Run : CliktCommand(help = "executes the solutions") {
    private val years = arrayOf(Y2015, Y2018, Y2022)

    private val year: Int? by option().int()
    private val day: Int? by option().int().restrictTo(1, 25)

    override fun run() {
        years
            .filter { y ->
                year?.let { y.year == it } ?: true
            }
            .forEach {
                it.execute(if (year != null) day else null)
            }
    }
}
