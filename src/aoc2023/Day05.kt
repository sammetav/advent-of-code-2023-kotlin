package aoc2023

import utils.readInput

fun main() {
    val (year, day) = "2023" to "Day05"

    data class SoilMap(val source: String, val dest: String, val mappings: List<Pair<LongRange, Long>>)

    fun List<String>.categorySeedValue(): Long {
        val mappings = ArrayList<SoilMap>()
        val seeds = ArrayList<Long>()
        val allSeeds = ArrayList<Long>()
        val regexNumber = Regex("\\d+")
        forEach { line ->
            when {
                line.startsWith("seeds:") -> {
                    regexNumber.findAll(line).map { it.value.toLong() }.toList().also { seeds.addAll(it) }

                    for (i in seeds.indices) {
                        if (i % 2 == 0 && i != seeds.lastIndex) {
                            for (j in (seeds[i]) until seeds[i] + seeds[i + 1]) {
                                allSeeds.add(j)
                            }
                        }
                    }
                }

                line.endsWith("map:") -> {
                    val nameParts = line.split(" ")[0].split("-")
                    val ranges = mutableListOf<Pair<LongRange, Long>>()
                    var index = indexOf(line)
                    while (true) {
                        index++
                        if (index >= size || this[index] == "") break
                        regexNumber.findAll(this[index]).map { it.value.toLong() }.toList().also {
                            ranges.add(Pair(LongRange(it[1], it[1] + it[2] - 1), it[0]))
                        }
                    }
                    mappings.add(SoilMap(nameParts[0], nameParts[2], ranges))
                }
            }
        }

        var minLocation = Long.MAX_VALUE
        for (seed in allSeeds) {
            var mapped = seed
            for (mapping in mappings) {
                for (r in mapping.mappings) {
                    if (r.first.contains(mapped)) {
                        mapped = r.second + (mapped - r.first.first)
                        break
                    }
                }
            }
            minLocation = minOf(minLocation, mapped)
        }
        return minLocation
    }

    fun part1(input: List<String>) = input.categorySeedValue()

    fun part2(input: List<String>) = input.categorySeedValue()

    /*
        val testInput1 = readInput(name = "${day}_p1_test", year = year)
    */
    val testInput2 = readInput(name = "${day}_p2_test", year = year)
    val input = readInput(name = day, year = year)

    /*
        check(part1(testInput1) == 35L)
    */
    /*
        println(part1(input))
    */

    check(part2(testInput2) == 46L)
    println(part2(input))
}
