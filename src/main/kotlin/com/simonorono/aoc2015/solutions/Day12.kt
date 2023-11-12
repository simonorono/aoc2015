package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day
import org.json.JSONArray
import org.json.JSONObject

object Day12 : Day(12) {
    private val json = JSON.fromString(getInput())

    private data class JSON(
        val obj: JSONObject?,
        val arr: JSONArray?,
    ) {
        private fun iterateObj(
            obj: JSONObject,
            ignoreRed: Boolean,
            callback: (Any) -> Unit
        ) {
            if (ignoreRed) {
                for (key in obj.keys()) {
                    val value = obj.get(key)
                    if (value is String && value == "red") {
                        return
                    }
                }
            }

            for (key in obj.keys()) {
                when (val value = obj.get(key)) {
                    is JSONObject -> iterateObj(value, ignoreRed, callback)
                    is JSONArray -> iterateArr(value, ignoreRed, callback)
                    else -> callback(value)
                }
            }
        }

        private fun iterateArr(
            arr: JSONArray,
            ignoreRed: Boolean,
            callback: (Any) -> Unit
        ) {
            for (i in 0..<arr.length()) {
                when (val value = arr.get(i)) {
                    is JSONObject -> iterateObj(value, ignoreRed, callback)
                    is JSONArray -> iterateArr(value, ignoreRed, callback)
                    else -> callback(value)
                }
            }
        }

        fun iterate(ignoreRed: Boolean, callback: (Any) -> Unit) {
            obj?.let { iterateObj(it, ignoreRed, callback) }
            arr?.let { iterateArr(it, ignoreRed, callback) }
        }

        companion object {
            fun fromString(str: String): JSON {
                val obj = try {
                    JSONObject(str)
                } catch (e: Exception) {
                    null
                }

                val arr = try {
                    JSONArray(str)
                } catch (e: Exception) {
                    null
                }

                return JSON(obj, arr)
            }
        }
    }

    override fun part1(): String {
        var result = 0

        json.iterate(false) { value ->
            if (value is Int) {
                result += value
            }
        }

        return result.toString()
    }

    override fun part2(): String {
        var result = 0

        json.iterate(true) { value ->
            if (value is Int) {
                result += value
            }
        }

        return result.toString()
    }
}
