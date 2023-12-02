package aoc2023

import utils.readInput

private val bagPossibility = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14,
)

fun main() {
    val (year, day) = "2023" to "Day02"

    fun String.supriseBagCounts(leastCount: Boolean): String {

        val sets = this.split(":")[1].split(";")
        return if (!leastCount) {
            val id = this.split(":")[0].split(" ")[1].toInt()
            val check = sets.all { set ->
                set.split(",").all { each ->
                    val number = each.filter { it.isDigit() }.toInt()
                    val color = each.filterNot { it.isDigit() }.replace("\\s".toRegex(), "")
                    number <= (bagPossibility[color] ?: 0)
                }
            }
            if (check) id.toString() else "0"
        } else {
            val leastCounts = mutableMapOf(
                "red" to 1,
                "green" to 1,
                "blue" to 1,
            )
            sets.map { set ->
                set.split(",").map { each ->
                    val number = each.filter { it.isDigit() }.toInt()
                    val color = each.filterNot { it.isDigit() }.replace("\\s".toRegex(), "")
                    val exitingCount = leastCounts[color]
                    if (exitingCount != null && exitingCount < number) {
                        leastCounts[color] = number
                    }
                }
            }
            leastCounts.values.reduce { acc, i -> acc * i }.toString()
        }
    }

    fun List<String>.bagOfCubes(leastCubes: Boolean) = this.sumOf { line ->
        val digits = line.supriseBagCounts(leastCubes)
        digits.toInt()
    }

    fun part1(input: List<String>) = input.bagOfCubes(leastCubes = false)

    fun part2(input: List<String>) = input.bagOfCubes(leastCubes = true)

    val testInput1 = readInput(name = "${day}_p1_test", year = year)
    val testInput2 = readInput(name = "${day}_p2_test", year = year)
    val input = readInput(name = day, year = year)

    check(part1(testInput1) == 8)
    println(part1(input))

    check(part2(testInput2) == 2286)
    println(part2(input))
}
