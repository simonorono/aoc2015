package com.simonorono.advent_of_code

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.restrictTo
import com.simonorono.advent_of_code.solutions.Year

class Run : CliktCommand(help = "executes the solutions") {
    private val day: Int? by option().int().restrictTo(1, 25)

    override fun run() {
        Year.execute(day)
    }
}
