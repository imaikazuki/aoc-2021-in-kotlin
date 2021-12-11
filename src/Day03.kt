fun main() {
    fun part1(input: List<String>): Int {
        val zeros = MutableList(input[0].length) { 0 }
        val ones = MutableList(input[0].length) { 0 }
        input.forEach { str -> str.forEachIndexed { index, c ->
            when(c) {
                '0' -> zeros[index]++
                '1' -> ones[index]++
            }
        } }
        val gamma = zeros
            .zip(ones)
            .map { (zero, one) -> if(one < zero) 1 else 0 }
            .reduce { acc, s -> acc * 2 + s }
        val epsilon = zeros
            .zip(ones)
            .map { (zero, one) -> if(zero < one) 1 else 0 }
            .reduce { acc, s -> acc * 2 + s }

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {

        val oxygen = (0..input.first().length).foldIndexed(input) {index, acc, _ -> if(1 == acc.size) acc else
            acc.filter { s -> s[index] == if(
                acc.filter { '0' == it[index] }.size <= acc.filter {'1' == it[index] }.size) '1' else '0'
        }}
            .first()
            .map { if (it == '0') 0 else 1 }
            .reduce { acc, s -> acc * 2 + s }
        val co2 = (0..input.first().length).foldIndexed(input) {index, acc, _ -> if(1 == acc.size) acc else
            acc.filter { s -> s[index] == if(
                acc.filter { '0' == it[index] }.size <= acc.filter {'1' == it[index] }.size) '0' else '1'
        }}
            .first()
            .map { if (it == '0') 0 else 1 }
            .reduce { acc, s -> acc * 2 + s }

        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}