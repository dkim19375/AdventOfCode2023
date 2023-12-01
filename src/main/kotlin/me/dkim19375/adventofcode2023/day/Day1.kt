package me.dkim19375.adventofcode2023.day

import me.dkim19375.adventofcode2023.AdventOfCodeDay

object Day1 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override fun solvePart1() {
        println(getInputString().lines().sumOf { line ->
            (line.first(Char::isDigit).toString() + line.last(Char::isDigit)).toInt()
        })
    }

    override fun solvePart2() {
        println(getInputString().lines().sumOf { line ->
            fun getDigit(i: Int): String? {
                val spelledOut = mapOf(
                    "one" to "1",
                    "two" to "2",
                    "three" to "3",
                    "four" to "4",
                    "five" to "5",
                    "six" to "6",
                    "seven" to "7",
                    "eight" to "8",
                    "nine" to "9"
                )
                if (line[i].isDigit()) return line[i].toString()
                for ((word, num) in spelledOut) {
                    if (i + word.length > line.length) continue
                    if (line.substring(i, i + word.length) == word) {
                        return num
                    }
                }
                return null
            }
            (getDigit(line.indices.first { getDigit(it) != null }) + getDigit(line.indices.last { getDigit(it) != null })).toInt()
        })
    }

}