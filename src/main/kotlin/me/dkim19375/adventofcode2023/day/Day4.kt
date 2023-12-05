package me.dkim19375.adventofcode2023.day

import me.dkim19375.adventofcode2023.AdventOfCodeDay
import kotlin.math.pow

object Day4 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    override fun solvePart1() {
        println(getInputString().lines().sumOf { line ->
            line.substringAfter(':').split('|').map(String::trim)
                .map { it.split(' ').map(String::trim).filter(String::isNotBlank).map(String::toInt) }
                .let { (winning, have) -> (2.0.pow(winning.count(have::contains) - 1.0)).toInt() }
        })
    }

    override fun solvePart2() {
        val cards = IntArray(getInputString().lines().size) { 1 }
        println(getInputString().lines().withIndex().sumOf { (cardNum, line) ->
            line.substringAfter(':').split('|').map(String::trim)
                .map { it.split(' ').map(String::trim).filter(String::isNotBlank).map(String::toInt) }
                .let { (winning, have) ->
                    ((cardNum + 1)..(cardNum + winning.count(have::contains))).forEach { cards[it] += cards[cardNum] }
                }
            cards[cardNum]
        })
    }
}