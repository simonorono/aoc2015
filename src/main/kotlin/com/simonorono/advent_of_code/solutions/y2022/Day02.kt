package com.simonorono.advent_of_code.solutions.y2022

import com.simonorono.advent_of_code.lib.Day

object Day02: Day(2022, 2) {
    private val input = getInput().lines().map {
        val (a, b) = it.split(" ")
        a to b
    }

    enum class Outcome(val value: Int) {
        Win(6),
        Draw(3),
        Loss(0);

        companion object {
            fun fromString(str: String): Outcome {
                return when(str) {
                    "X" -> Loss
                    "Y" -> Draw
                    "Z" -> Win
                    else -> throw IllegalStateException()
                }
            }
        }
    }

    enum class Play(val value: Int) {
        Rock(1),
        Paper(2),
        Scissors(3);

        fun outcome(other: Play): Outcome {
            return when(this) {
                Rock -> when (other) {
                    Rock -> Outcome.Draw
                    Paper -> Outcome.Loss
                    Scissors -> Outcome.Win
                }
                Paper -> when (other) {
                    Rock -> Outcome.Win
                    Paper -> Outcome.Draw
                    Scissors -> Outcome.Loss
                }
                Scissors -> when (other) {
                    Rock -> Outcome.Loss
                    Paper -> Outcome.Win
                    Scissors -> Outcome.Draw
                }
            }
        }

        companion object {
            fun fromString(str: String): Play {
                return when(str) {
                    "A", "X" -> Rock
                    "B", "Y" -> Paper
                    "C", "Z" -> Scissors
                    else -> throw IllegalStateException()
                }
            }

            fun forOutcome(other: Play, outcome: Outcome): Play {
                return when (outcome) {
                    Outcome.Draw -> other
                    Outcome.Win -> when (other) {
                        Rock -> Paper
                        Paper -> Scissors
                        Scissors -> Rock
                    }
                    Outcome.Loss -> when(other) {
                        Rock -> Scissors
                        Paper -> Rock
                        Scissors -> Paper
                    }
                }
            }
        }
    }

    override fun part1(): String {
        var result = 0

        for ((enemy, me) in this.input) {
            val enemyPlay = Play.fromString(enemy)
            val myPlay = Play.fromString(me)

            result += myPlay.outcome(enemyPlay).value + myPlay.value
        }

        return result.toString()
    }

    override fun part2(): String {
        var result = 0

        for ((enemy, outcome) in this.input) {
            val enemyPlay = Play.fromString(enemy)
            val desiredOutcome = Outcome.fromString(outcome)
            val myPlay = Play.forOutcome(enemyPlay, desiredOutcome)

            result += desiredOutcome.value + myPlay.value
        }

        return result.toString()
    }
}