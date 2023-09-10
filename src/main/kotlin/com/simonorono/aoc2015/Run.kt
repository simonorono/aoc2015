package com.simonorono.aoc2015

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.restrictTo
import com.simonorono.aoc2015.solutions.Year

class Run : CliktCommand(help = "executes the solutions") {
    private val day: Int? by option().int().restrictTo(1, 25)

    override fun run() {
        Year.execute(day)
    }
}
