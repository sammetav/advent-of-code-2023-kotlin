package utils

import kotlin.io.path.Path
import kotlin.io.path.readLines

private fun pathFrom(name: String, year: String) = Path("inputs/aoc$year/$name.txt")

fun readInput(name: String, year: String) = pathFrom(name, year).readLines()
