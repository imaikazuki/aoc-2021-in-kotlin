fun main() {
    fun part1(input: List<String>): Int {
        val values = input
            .filter { str -> "" != str }
            .map { str -> str.toInt() }
        return values
            .subList(1, values.size)
            .zip(values)
            .count { (current, previous) -> previous < current }
    }

    fun part2(input: List<String>): Int {
        val values = input
            .filter { str -> "" != str }
            .map { str -> str.toInt() }
        val sumOfDepth = values
            .subList(0, values.size - 2)
            .mapIndexed { index, depth -> depth + values[index+1] + values[index+2] }
        return sumOfDepth
            .subList(1, sumOfDepth.size)
            .zip(sumOfDepth)
            .count { (current, previous) -> previous < current }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
