import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val data = input
            .asSequence()
            .filter { it.isNotEmpty() }
            .map { it.replace("->", ",")}
            .map { it.replace(" ", "")}
            .map { it.split(",") }
            .filter { it[0] == it [2] || it[1] == it[3]}
            .map { chars -> chars.map { it.toInt() }}
            .map { Pair(Pair(it[0], it[1]), Pair(it[2], it[3])) }
            .toList()
        val width = data
            .map { if (it.first.first < it.second.first) it.second.first else it.first.first }
            .maxOf { it } + 1
        val height = data
            .map { if (it.first.second < it.second.second) it.second.second else it.first.second }
            .maxOf { it } + 1
        val diagram = MutableList(height) { MutableList(width) { 0 } }
        data.forEach {
                if(it.first.first == it.second.first) {
                    val from = if(it.first.second < it.second.second) it.first.second else it.second.second
                    val to = if(it.first.second < it.second.second) it.second.second else it.first.second
                    (from..to).forEach { i -> diagram[i][it.first.first]++ }
                } else { // it.first.second == it.second.second
                    val from = if(it.first.first < it.second.first) it.first.first else it.second.first
                    val to = if(it.first.first < it.second.first) it.second.first else it.first.first
                    (from..to).forEach { i -> diagram[it.first.second][i]++ }
                }
            }
        return diagram.flatten().count { 2 <= it }
    }

    fun part2(input: List<String>): Int {
        val data = input
            .asSequence()
            .filter { it.isNotEmpty() }
            .map { it.replace("->", ",")}
            .map { it.replace(" ", "")}
            .map { it.split(",") }
            .map { chars -> chars.map { it.toInt() }}
            .map { Pair(Pair(it[0], it[1]), Pair(it[2], it[3])) }
            .toList()
        val width = data
            .map { if (it.first.first < it.second.first) it.second.first else it.first.first }
            .maxOf { it } + 1
        val height = data
            .map { if (it.first.second < it.second.second) it.second.second else it.first.second }
            .maxOf { it } + 1
        val diagram = MutableList(height) { MutableList(width) { 0 } }
        data.forEach { record ->
            val cols =
                if(record.first.first == record.second.first) List(abs(record.first.second-record.second.second)+1) { record.first.first }
                else if(record.first.first < record.second.first) (record.first.first..record.second.first)
                else (record.second.first..record.first.first).reversed()
            val rows =
                if(record.first.second == record.second.second) List(abs(record.first.first-record.second.first)+1) { record.first.second }
                else if(record.first.second < record.second.second) (record.first.second..record.second.second)
                else (record.second.second..record.first.second).reversed()
            rows.zip(cols).forEach { diagram[it.first][it.second]++ }
        }
        return diagram.flatten().count { 2 <= it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
