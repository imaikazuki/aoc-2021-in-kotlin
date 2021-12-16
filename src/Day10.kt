fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { str ->
            val stack = ArrayDeque<Char>()
            val result = str.map { char ->
                when (char) {
                    '(', '[', '{', '<' -> {
                        stack.add(char); 0
                    }
                    ')' -> if (stack.isEmpty() || '(' == stack.removeLast()) 0 else 3
                    ']' -> if (stack.isEmpty() || '[' == stack.removeLast()) 0 else 57
                    '}' -> if (stack.isEmpty() || '{' == stack.removeLast()) 0 else 1197
                    '>' -> if (stack.isEmpty() || '<' == stack.removeLast()) 0 else 25137
                    else -> 0
                }
            }
            if (result.all { it == 0 }) 0 else result.first { it != 0 }
        }
    }

    fun part2(input: List<String>): Long {
        val result =  input.filter { str ->
            val stack = ArrayDeque<Char>()
            val result = str.map { char ->
                when (char) {
                    '(', '[', '{', '<' -> {
                        stack.add(char); 0
                    }
                    ')' -> if (stack.isEmpty() || '(' == stack.removeLast()) 0 else 3
                    ']' -> if (stack.isEmpty() || '[' == stack.removeLast()) 0 else 57
                    '}' -> if (stack.isEmpty() || '{' == stack.removeLast()) 0 else 1197
                    '>' -> if (stack.isEmpty() || '<' == stack.removeLast()) 0 else 25137
                    else -> 0
                }
            }
            result.all { 0 == it }
        }.map { str ->
            val stack = ArrayDeque<Char>()
            str.forEach {
                when (it) {
                    '(', '[', '{', '<' -> stack.add(it)
                    ')', ']', '}', '>' -> stack.removeLast()
                }
            }
            val closingCharacters = MutableList(0) {' '}
            while (stack.isNotEmpty()) {
                when (stack.removeLast()) {
                    '(' -> closingCharacters.add(')')
                    '[' -> closingCharacters.add(']')
                    '{' -> closingCharacters.add('}')
                    '<' -> closingCharacters.add('>')
                }
            }
            closingCharacters
        }.map { chars ->
            chars.fold(0) { acc: Long, c ->
                acc * 5 + when (c) {
                    ')' -> 1
                    ']' -> 2
                    '}' -> 3
                    '>' -> 4
                    else -> 0
                }
            }
        }.sorted()
        println(result)
        return result[result.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
