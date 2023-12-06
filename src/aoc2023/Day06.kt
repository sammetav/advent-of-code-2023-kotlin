package aoc2023

import utils.readInput

fun main() {
    val (year, day) = "2023" to "Day06"

    fun List<String>.calculateTimesExceedingDistance(): Int {
        val finals = ArrayList<Int>()
        val timeRecords = this[0].filter { it.isDigit() }.toLong()
        val distanceRecords = this[1].filter { it.isDigit() }.toLong()
        val recordMap = mapOf(timeRecords to distanceRecords)
        recordMap.forEach { (time, distance) ->
            var times = 0
            var holdTime = 0L
            while (holdTime <= time) {
                val travelTime = time - holdTime
                val totalDistance = travelTime * holdTime
                if (totalDistance > distance) {
                    times++
                }
                holdTime++
            }
            finals.add(times)
        }
        return finals.reduce { acc, i -> acc * i }
    }

    fun List<String>.sumFilteredDigits(considerGaps: Boolean) = this.calculateTimesExceedingDistance()

    fun part1(input: List<String>) = input.sumFilteredDigits(considerGaps = false)

/*
    val testInput1 = readInput(name = "${day}_p1_test", year = year)
*/
    val testInput2 = readInput(name = "${day}_p2_test", year = year)
    val input = readInput(name = day, year = year)

/*
    check(part1(testInput1) == 288)
*/
    check(part1(testInput2) == 71503)
    println(part1(input))
}
