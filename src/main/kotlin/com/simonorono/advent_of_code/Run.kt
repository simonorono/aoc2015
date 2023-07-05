package com.simonorono.advent_of_code

import com.github.ajalt.clikt.core.CliktCommand
import com.simonorono.advent_of_code.solutions.y2015.Y2015
import com.simonorono.advent_of_code.solutions.y2022.Y2022

class Run : CliktCommand(help = "executes the solutions") {
    private val years = arrayOf(Y2015, Y2022)

    override fun run() {
        years.forEach { it.execute() }
    }
}
