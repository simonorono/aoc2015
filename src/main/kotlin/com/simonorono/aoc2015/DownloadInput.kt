package com.simonorono.aoc2015

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import java.io.File
import java.net.URI
import java.nio.charset.Charset

class DownloadInput : CliktCommand(help = "downloads your input files") {
    private val sessionCookie by argument(name = "session cookie")

    private fun getInput(day: Int): String {
        val url = URI("https://adventofcode.com/2015/day/$day/input").toURL()

        return url.openConnection().apply {
            setRequestProperty("Cookie", "session=$sessionCookie")
        }.getInputStream().readAllBytes().toString(Charset.defaultCharset())
    }

    private fun writeToFile(day: Int, content: String) {
        File("src/main/resources/input").mkdirs()

        val file = File("src/main/resources/input/day$day.txt")

        if (!file.exists()) {
            file.createNewFile()
        }

        file.writeText(content.trimEnd())
    }

    override fun run() {
        for (day in 1..25) {
            writeToFile(day, getInput(day))
        }
    }
}
