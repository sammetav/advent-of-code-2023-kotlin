package aoc2023

import utils.readInput

fun main() {
    val (year, day) = "2023" to "Day04"


    fun String.checkMatches(): String {
        var count = 0
        var isInitialMatch = true
        val numbers = this.split(":")[1].split("|")
        val winningNums = numbers[0].trim().split("\\s+".toRegex()).map { it.toInt() }
        val existing = numbers[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        winningNums.forEachIndexed { index, i ->
            if (existing.contains(i)) {
                count = if (isInitialMatch) {
                    isInitialMatch = false
                    1
                } else {
                    count * 2
                }
            }
        }

        return count.toString()
    }

    fun parseMatches(input: List<String>): Map<Int, Int> {
        return input.associate { line ->
            val (card, game) = line.split(":")
            val cardId = card.split("\\s+".toRegex())[1].toInt()
            val (winningNumbers, yourNumbers) = game.split("|")

            val winningNumberList = winningNumbers.trim().split("\\s+".toRegex())
            val yourNumberList = yourNumbers.trim().split("\\s+".toRegex())

            val matches = yourNumberList.count { it in winningNumberList }

            cardId to matches
        }
    }

    fun List<String>.bagOfCubes() = this.sumOf { line ->
        val digits = line.checkMatches()
        digits.toInt()
    }

    fun part1(input: List<String>) = input.bagOfCubes()

    fun part2(input: List<String>): Int {
        val cards = parseMatches(input)

        val scratchcards = MutableList(cards.size) {
            0
        }
        cards.forEach { (id, score) ->
            val currentIndex = id - 1
            scratchcards[currentIndex]++

            for (i in 1..score) {
                if (currentIndex + i in scratchcards.indices) {
                    scratchcards[currentIndex + i] += scratchcards[currentIndex]
                }
            }
        }
        return scratchcards.sum()
    }

    val testInput1 = readInput(name = "${day}_p1_test", year = year)
    val testInput2 = readInput(name = "${day}_p2_test", year = year)

    val input = readInput(name = day, year = year)

    check(part1(testInput1) == 13)
    println(part1(input))

    check(part2(testInput2) == 30)
    println(part2(input))
}
