fun main() {
    fun part1(input: List<String>): Int {
        val schoolOfLanternfish = input[0].split(",").map { it.toInt() }.toMutableList()
        (1..80).forEach { _ ->
            val birth = schoolOfLanternfish.count { 0 == it }
            schoolOfLanternfish.forEachIndexed { index, it -> if(0 == it) schoolOfLanternfish[index] = 6 else schoolOfLanternfish[index]-- }
            (1..birth).forEach { _ -> schoolOfLanternfish.add(8) }
        }
        return schoolOfLanternfish.size
    }

    fun part2(input: List<String>): Long {

        var schoolOfLanternfish = List(9) { 0L }.toMutableList()
        input[0].split(",").map { it.toInt() }.forEach { schoolOfLanternfish[it]++ }
        (1..256).forEach { _ ->
            schoolOfLanternfish = listOf(
                schoolOfLanternfish[1],
                schoolOfLanternfish[2],
                schoolOfLanternfish[3],
                schoolOfLanternfish[4],
                schoolOfLanternfish[5],
                schoolOfLanternfish[6],
                schoolOfLanternfish[0] + schoolOfLanternfish[7],
                schoolOfLanternfish[8],
                schoolOfLanternfish[0]
            ).toMutableList()
        }
        return schoolOfLanternfish.fold(0L) { acc, it -> acc + it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
