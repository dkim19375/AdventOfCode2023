@file:Suppress("DuplicatedCode")

package me.dkim19375.adventofcode2023.day

import me.dkim19375.adventofcode2023.AdventOfCodeDay

object Day3 : AdventOfCodeDay() {

    @JvmStatic
    fun main(args: Array<String>) = solve()

    private const val NOTHING = -1
    private const val SYMBOL = -2

    override fun solvePart1() {
        val numbers = mutableMapOf<Pair<Int, Int>, Int>()
        val schematic = getInputString().lines().mapIndexed { y, line ->
            var currentNum = ""
            val result = line.mapIndexed charMap@{ x, char ->
                if (char.isDigit()) {
                    currentNum += char
                    return@charMap char.digitToInt()
                }
                if (currentNum.isNotEmpty()) {
                    numbers[y to (x - currentNum.length)] = currentNum.toInt()
                    currentNum = ""
                }
                if (char == '.') return@charMap NOTHING
                SYMBOL
            }
            if (currentNum.isNotEmpty()) {
                numbers[y to (line.length - currentNum.length)] = currentNum.toInt()
                currentNum = ""
            }
            result
        }
        val partNumbers = mutableListOf<Int>()
        for ((numCoords, num) in numbers) {
            val (numY, numMinX) = numCoords
            val numMaxX = numMinX + num.toString().length - 1

            for ((testY, testX) in ((numY - 1)..(numY + 1)).flatMap { y ->
                ((numMinX - 1)..(numMaxX + 1)).map { x -> y to x }
            }) {
                if (testY !in schematic.indices || testX !in schematic[testY].indices) continue
                if (schematic[testY][testX] != SYMBOL) continue
                partNumbers.add(num)
                break
            }
        }
        println(partNumbers.sum())
    }

    override fun solvePart2() {
        val numbers = mutableMapOf<Pair<Int, Int>, Int>()
        val schematic = getInputString().lines().mapIndexed { y, line ->
            var currentNum = ""
            val result = line.mapIndexed charMap@{ x, char ->
                if (char.isDigit()) {
                    currentNum += char
                    return@charMap char.digitToInt()
                }
                if (currentNum.isNotEmpty()) {
                    numbers[y to (x - currentNum.length)] = currentNum.toInt()
                    currentNum = ""
                }
                if (char != '*') return@charMap NOTHING
                SYMBOL
            }
            if (currentNum.isNotEmpty()) {
                numbers[y to (line.length - currentNum.length)] = currentNum.toInt()
                currentNum = ""
            }
            result
        }
        val gears = mutableMapOf<Pair<Int, Int>, List<Int>>()
        for ((numCoords, num) in numbers) {
            val (numY, numMinX) = numCoords
            val numMaxX = numMinX + num.toString().length - 1

            for ((testY, testX) in ((numY - 1)..(numY + 1)).flatMap { y ->
                ((numMinX - 1)..(numMaxX + 1)).map { x -> y to x }
            }) {
                if (testY !in schematic.indices || testX !in schematic[testY].indices) continue
                if (schematic[testY][testX] != SYMBOL) continue
                gears[testX to testY] = gears.getOrDefault(testX to testY, emptyList()) + num
                break
            }
        }
        println(gears.values.sumOf { list -> list.takeIf { it.size == 2 }?.reduce(Int::times) ?: 0 })
    }
}