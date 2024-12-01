import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val l1 = mutableListOf<Int>()
        val l2 = mutableListOf<Int>()
        input.forEach {
            l1.add(it.split("   ")[0].toInt())
            l2.add(it.split("   ")[1].toInt())
        }

        return l1.sorted().zip(l2.sorted()).sumOf {
            abs(it.first - it.second)
        }
    }

    fun part2(input: List<String>): Int {
        val counts = buildMap<Int, Int> {
            input.forEach {
                val i = it.split("   ")[1].toInt()
                put(i, getOrDefault(i, 0) + 1)
            }
        }

        return input.sumOf {
            val i = it.split("   ")[0].toInt()
            counts.getOrDefault(i, 0) * i
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput), 11)
    check(part2(testInput), 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
