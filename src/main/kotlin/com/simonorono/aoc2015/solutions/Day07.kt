package com.simonorono.aoc2015.solutions

import com.simonorono.aoc2015.lib.Day

object Day07 : Day(7) {
    private val input = getInput().lines().map { parseInput(it) }
    private val values = mutableMapOf<String, UShort?>()

    private enum class Operator {
        OR,
        AND,
        LSHIFT,
        RSHIFT,
        NOT,
        YES;

        companion object {
            fun fromString(value: String): Operator {
                return when (value) {
                    "OR" -> OR
                    "AND" -> AND
                    "LSHIFT" -> LSHIFT
                    "RSHIFT" -> RSHIFT
                    "NOT" -> NOT
                    "YES" -> YES
                    else -> throw IllegalStateException()
                }
            }
        }
    }

    private abstract class Op {
        abstract val target: String

        abstract fun calculate(): UShort
    }

    private data class BinaryOp(
        val left: String,
        val right: String,
        val op: Operator,
        override val target: String
    ) : Op() {
        override fun calculate(): UShort {
            val left = getValue(left)
            val right = getValue(right)

            val res = when (op) {
                Operator.OR -> left.or(right)
                Operator.AND -> left.and(right)
                Operator.LSHIFT -> left.toUInt().shl(right.toInt()).toUShort()
                Operator.RSHIFT -> left.toUInt().shr(right.toInt()).toUShort()
                else -> throw IllegalStateException()
            }

            values[target] = res

            return res
        }
    }

    private data class UnaryOp(
        val operand: String,
        val op: Operator,
        override val target: String
    ) : Op() {
        override fun calculate(): UShort {
            val operand = getValue(operand)

            val res = when (op) {
                Operator.NOT -> operand.inv()
                Operator.YES -> operand
                else -> throw IllegalStateException()
            }

            values[this.operand] = res
            return res
        }
    }

    private fun getValue(target: String): UShort {
        val result = values[target]
        if (result != null) {
            return result
        }

        if (target.matches("""\d+""".toRegex())) {
            return target.toUShort()
        }

        val op = input.find { it.target == target }!!
        return op.calculate()
    }

    private fun parseInput(line: String): Op {
        val parts = line.split(" -> ")
        val target = parts[1]

        val operation = parts[0].split(" ")

        return when (operation.size) {
            3 -> BinaryOp(
                operation[0],
                operation[2],
                Operator.fromString(operation[1]),
                target
            )

            2 -> UnaryOp(
                operation[1],
                Operator.fromString(operation[0]),
                target
            )

            1 -> UnaryOp(
                operation[0],
                Operator.YES,
                target
            )

            else -> throw IllegalStateException("")
        }
    }

    override fun part1(): String {
        return getValue("a").toString()
    }

    override fun part2(): String {
        val a = getValue("a")
        values.clear()
        values["b"] = a

        return getValue("a").toString()
    }
}