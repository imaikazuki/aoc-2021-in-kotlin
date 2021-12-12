fun main() {
    fun part1(input: List<String>): Int {
        return input
            .filter { it.isNotEmpty() }.sumOf {
                it.split(" | ")[1]
                    .split(" ")
                    .count { s -> 2 == s.length || 4 == s.length || 3 == s.length || 7 == s.length }
            }
    }

    fun part2(input: List<String>): Int {
        val digits = input
            .filter { it.isNotEmpty() }
            .map { line ->
                line.split(" | ")[0]
                    .split(" ")
                    .map { String(it.toCharArray().sortedArray()) }
            }.map { record ->
                val dict = MutableList(10) { "" }
                dict[1] = record.single { 2 == it.length }
                dict[4] = record.single { 4 == it.length }
                dict[7] = record.single { 3 == it.length }
                dict[8] = record.single { 7 == it.length }
                dict[3] = record.single { 5 == it.length && it.toList().containsAll(dict[1].toSet()) }
                dict[9] = record.single { 6 == it.length && it.toList().containsAll(dict[4].toSet()) }
                dict[5] = record.single { 5 == it.length && dict[9] == String((it + dict[1]).toList().distinct().sorted().toCharArray()) }
                dict[2] = record.single { 5 == it.length && it != dict[3] && it != dict[5] }
                dict[6] = record.single { 6 == it.length && (it.contains(dict[1][0]) xor it.contains(dict[1][1])) }
                dict[0] = record.single { 6 == it.length && it != dict[6] && it != dict[9] }
                dict.toList()
            }
        val data = input
            .filter { it.isNotEmpty() }
            .map { line ->
                line.split(" | ")[1]
                    .split(" ")
                    .map { String(it.toCharArray().sortedArray()) }}
        return digits.zip(data).sumOf { record ->
            record.first.indexOf(record.second[0]) * 1000 +
                    record.first.indexOf(record.second[1]) * 100 +
                    record.first.indexOf(record.second[2]) * 10 +
                    record.first.indexOf(record.second[3])
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
