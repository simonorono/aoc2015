package com.simonorono.advent_of_code

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class AOC : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) {
    AOC().subcommands(DownloadInput()).main(args)
}
