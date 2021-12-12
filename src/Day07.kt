import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }
        val min = positions.minOf { it }
        val max = positions.maxOf { it }
        return (min..max)
            .map { goal -> positions.map { abs(goal - it) }.sumOf { it } }
            .minOf { it }
    }

    fun part2(input: List<String>): Int {
        val positions = input.first().split(",").map { it.toInt() }
        val min = positions.minOf { it }
        val max = positions.maxOf { it }
        return (min..max)
            .map { goal -> positions.map { abs(goal - it) * (abs(goal - it)+1) / 2 }.sumOf { it } }
            .minOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
