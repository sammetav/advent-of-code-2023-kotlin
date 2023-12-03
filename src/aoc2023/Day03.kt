package aoc2023

import utils.readInput
import kotlin.math.abs

fun main() {
    val (year, day) = "2023" to "Day03"

    fun engineParts(input: List<String>): Int {
        val partNums = ArrayList<PartNum>()
        val splChars = ArrayList<SplChar>()

        input.forEachIndexed { y, l ->
            Regex("""\d+|[^\w\d\.]+""").findAll(l).forEach { r ->
                r.groups.filterNotNull().forEach { g ->
                    if (g.value[0].isDigit())
                        partNums.add(PartNum(g.value.toInt(), g.range.map { x -> Point(x, y) }))
                    else
                        splChars.add(SplChar(g.value, Point(g.range.first, y)))
                }
            }
        }

/*        return partNums.filter { p ->
            splChars.any { s -> p.loc.any { it.adjacent(s.loc) } }
        }.sumOf { p -> p.value }*/

        return splChars.filter { g ->
            (g.char == "*") and (partNums.count { p ->
                p.loc.any { it.adjacent(g.loc) }
            } == 2)
        }.sumOf { g ->
            partNums.filter { p ->
                p.loc.any { it.adjacent(g.loc) }
            }.map { it.value }.reduce(Int::times)
        }
    }

    fun part1(input: List<String>) = engineParts(input)

    fun part2(input: List<String>) = engineParts(input)

    val testInput1 = readInput(name = "${day}_p1_test", year = year)
    val testInput2 = readInput(name = "${day}_p2_test", year = year)
    val input = readInput(name = day, year = year)

/*
    check(part1(testInput1) == 4361)
*/
    check(part2(testInput2) == 467835)
    println(part2(input))

}

data class PartNum(val value: Int, val loc: List<Point>)

data class Point(val x: Int, val y: Int) {
    fun adjacent(other: Point): Boolean {
        return (abs(other.x - this.x) <= 1) and (abs(other.y - this.y) <= 1)
    }
}

data class SplChar(val char: String, val loc: Point)


