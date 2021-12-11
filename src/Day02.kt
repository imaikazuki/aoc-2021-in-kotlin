fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontalPosition = 0
        input.filter { str -> str.isNotEmpty() }
            .map { str -> str.split(" ") }
            .forEach { (command, parameter) ->
                when(command) {
                    "forward" -> horizontalPosition += parameter.toInt()
                    "down" -> depth += parameter.toInt()
                    "up" -> depth -= parameter.toInt()
                }
            }
        return depth * horizontalPosition
    }

    fun part2(input: List<String>): Int {
        var aim = 0; var depth = 0; var horizontalPosition = 0
        input.filter { str -> str.isNotEmpty() }
            .map { str -> str.split(" ") }
            .forEach { (command, parameter) ->
                when(command) {
                    "forward" -> { horizontalPosition += parameter.toInt() ; depth += aim * parameter.toInt() }
                    "down" -> aim += parameter.toInt()
                    "up" -> aim -= parameter.toInt()
                }
            }
        return depth * horizontalPosition
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}