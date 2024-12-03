import kotlin.math.abs

fun main() {
	fun part1(input: List<String>): Long {
		val string = input.joinToString()
		val parts = """mul\(\d+,\d+\)""".toRegex().findAll(string).map { it.value }
		return parts.sumOf {
			val (a, b) = it.removePrefix("mul(").removeSuffix(")").split(",")
			a.toLong() * b.toLong()
		}
	}

	fun part2(input: List<String>): Long {
		val string = input.joinToString()
		val parts = """mul\(\d+,\d+\)""".toRegex().findAll(string)
		val doRanges = mutableListOf<Int>()
		val dontRanges = mutableListOf<Int>()
		var idx = 0
		while (string.indexOf("do()", idx) >= 0) {
			idx = string.indexOf("do()", idx)
			doRanges.add(idx)
			idx += 1
		}
		idx = 0
		while (string.indexOf("don't()", idx) >= 0) {
			idx = string.indexOf("don't()", idx)
			dontRanges.add(idx)
			idx += 1
		}

		return parts.sumOf { part ->
			val lastDo = doRanges.lastOrNull { it < part.range.first }
			val lastDont = dontRanges.lastOrNull { it < part.range.first }

			if (lastDont == null) {
				val (a, b) = part.value.removePrefix("mul(").removeSuffix(")").split(",")
				a.toLong() * b.toLong()
			} else if (lastDo == null) {
				0
			} else {
				if (lastDo > lastDont) {
					val (a, b) = part.value.removePrefix("mul(").removeSuffix(")").split(",")
					a.toLong() * b.toLong()
				} else {
					0
				}
			}
		}
	}

	val testInput = readInput("Day03_test")
	check(part1(testInput), 161L)
	check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")), 48L)

	val input = readInput("Day03")
	part1(input).println()
	part2(input).println()
}
