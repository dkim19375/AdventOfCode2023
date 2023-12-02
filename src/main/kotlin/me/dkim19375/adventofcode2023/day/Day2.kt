@file:Suppress("DuplicatedCode")

package me.dkim19375.adventofcode2023.day

import me.dkim19375.adventofcode2023.AdventOfCodeDay
import kotlin.math.max

object Day2 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override fun solvePart1() {
        val limits = mapOf("red" to 12, "green" to 13, "blue" to 14)
        println(getInputString().lines().sumOf { line ->
            val id = line.dropWhile { !it.isDigit() }.takeWhile(Char::isDigit).toInt()
            line.split(": ")[1].split("; ").forEach { round ->
                round.split(", ").forEach { marbles ->
                    marbles.split(" ").let { (amount, color) ->
                        val limit = limits[color] ?: throw IllegalStateException("Color $color not found in limits")
                        if (amount.toInt() > limit) {
                            return@sumOf 0
                        }
                    }
                }
            }
            id
        })
    }

    override fun solvePart2() {
        println(getInputString().lines().sumOf { line ->
            val max = mutableMapOf<String, Int>()
            line.split(": ")[1].split("; ").forEach { round ->
                round.split(", ").forEach { marbles ->
                    marbles.split(" ").let { (amount, color) ->
                        max[color] = max(max[color] ?: 0, amount.toInt())
                    }
                }
            }
            max.values.fold(1, Int::times)
        })
    }

    /*override fun getInputString(): String = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent()*/
}