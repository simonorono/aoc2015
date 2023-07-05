package com.simonorono.advent_of_code

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import java.io.File
import java.net.URL
import java.nio.charset.Charset

const val MIN_YEAR = 2015
const val MAX_YEAR = 2022

class DownloadInput : CliktCommand(help = "downloads your input files") {
    private val sessionCookie by argument(name = "session cookie")

    private fun getInput(year: Int, day: Int): String {
        val url = URL("https://adventofcode.com/$year/day/$day/input")

        return url.openConnection().apply {
            setRequestProperty("Cookie", "session=$sessionCookie")
        }.getInputStream().readAllBytes().toString(Charset.defaultCharset())
    }

    private fun writeToFile(year: Int, day: Int, content: String) {
        File("src/main/resources/input/$year").mkdirs()

        val file = File("src/main/resources/input/$year/day$day.txt")

        if (!file.exists()) {
            file.createNewFile()
        }

        file.writeText(content)
    }

    override fun run() {
        for (year in MIN_YEAR..MAX_YEAR) {
            for (day in 1..25) {
                writeToFile(year, day, getInput(year, day))
            }
        }
    }
}
