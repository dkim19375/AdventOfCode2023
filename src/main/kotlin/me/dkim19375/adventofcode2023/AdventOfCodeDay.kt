package me.dkim19375.adventofcode2023

import java.io.FileNotFoundException

abstract class AdventOfCodeDay {

    fun solve() {
        println("Day $day:")
        println("Part 1:")
        runCatching(this::solvePart1).onFailure { it.printStackTrace() }
        println("Part 2:")
        runCatching(this::solvePart2).onFailure { it.printStackTrace() }
    }

    abstract fun solvePart1()

    abstract fun solvePart2()

    open fun getInputString(): String = readFile()

    private val day: Int = javaClass.simpleName.drop(3).toInt()

    private fun readFile(): String = javaClass.getResource("day$day.txt")?.readText()
        ?: throw FileNotFoundException(
            "File day$day.txt not found! (${javaClass.packageName.replace('.', '/')}/day$day.txt)"
        )
}