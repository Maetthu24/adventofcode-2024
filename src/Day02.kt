import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input.count {
            val levels = it.split(" ")
            if (levels[0].toLong() == levels[1].toLong()) {
                return@count false
            }

            val goesUp = levels[0].toLong() < levels[1].toLong()

            levels.windowed(2).forEach { (a, b) ->
                val diff = abs(a.toLong() - b.toLong())
                if (diff < 1L || diff > 3L) {
                    return@count false
                } else if (goesUp && b.toLong() <= a.toLong()) {
                    return@count false
                } else if (!goesUp && b.toLong() >= a.toLong()) {
                    return@count false
                }
            }
			return@count true
        }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val levels = it.split(" ")

            val permutations = levels.indices.map { i ->
                val new = levels.toMutableList()
                new.removeAt(i)
                new.toList()
            }

            fun check(levels: List<String>): Boolean {
                if (levels[0].toLong() == levels[1].toLong()) {
                    return false
                }

                val goesUp = levels[0].toLong() < levels[1].toLong()

                levels.windowed(2).forEach { (a, b) ->
                    val diff = abs(a.toLong() - b.toLong())
                    if (diff < 1L || diff > 3L) {
                        return false
                    } else if (goesUp && b.toLong() <= a.toLong()) {
                        return false
                    } else if (!goesUp && b.toLong() >= a.toLong()) {
                        return false
                    }
                }
                return true
            }

            return@count permutations.count { p -> check(p) } > 0
        }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput), 2)
    check(part2(testInput), 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
