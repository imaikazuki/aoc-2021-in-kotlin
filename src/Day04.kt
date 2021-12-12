fun main() {
    fun doesWin(numbers: List<Int>, board: List<List<Int>>): Boolean {
        return board.map { row -> numbers.containsAll(row) }.any { it } ||
                (0 until board.first().size)
                    .map { colnum -> board.map { row -> row[colnum]}}
                    .map { col -> numbers.containsAll(col) }
                    .any { it }
    }

    fun score(numbers: List<Int>, board: List<List<Int>>): Int {
        return if(doesWin(numbers, board)) board.flatten().filter { ! numbers.contains(it) }.sum() else 0
    }

    fun part1(input: List<String>): Int {
        val numbers = input.first().split(",").map { it.toInt() }
        val cols = input[2].replace("  ", " ").split(" ").size
        val rowBoards = input.subList(1, input.size).filter { it.isNotEmpty() }
        val boards = (0 until rowBoards.size / cols)
            .map { nth -> (0 until cols).map { col -> rowBoards[(nth)*cols + col] }}
            .map { board -> board.map { it.trim().replace("  ", " ").split(" ").map { s -> s.toInt() }}}

        return (numbers.indices)
            .mapIndexed { index, _ -> numbers.subList(0, index + 1) }
            .map { nbs -> boards.map { board -> score(nbs, board) * nbs.last() }.filter { 0 < it } }
            .first { it.isNotEmpty() }
            .first()
    }

    fun part2(input: List<String>): Int {
        val numbers = input.first().split(",").map { it.toInt() }
        val cols = input[2].replace("  ", " ").split(" ").size
        val rowBoards = input.subList(1, input.size).filter { it.isNotEmpty() }
        val boards = (0 until rowBoards.size / cols)
            .map { nth -> (0 until cols).map { col -> rowBoards[(nth)*cols + col] }}
            .map { board -> board.map { it.trim().replace("  ", " ").split(" ").map { s -> s.toInt() }}}

        val tmp00 = (numbers.indices)
            .mapIndexed { index, _ -> numbers.subList(0, index + 1) }
            .map { nbs -> boards
                .filter { ! doesWin(nbs.subList(0, nbs.size-1), it) }
                .map { board -> score(nbs, board) * nbs.last() }
                .filter { 0 < it } }
        val tmp10 = tmp00   .last { it.isNotEmpty() }
        val tmp20 = tmp10    .last()
        return tmp20
    }

    check(!doesWin(listOf(0), listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 10),
        listOf(11, 12, 13, 14, 15),
        listOf(16, 17, 18, 19, 20),
        listOf(21, 22, 23, 24, 25)
    )))

    check(doesWin(listOf(1, 2, 3, 4, 5), listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 10),
        listOf(11, 12, 13, 14, 15),
        listOf(16, 17, 18, 19, 20),
        listOf(21, 22, 23, 24, 25)
    )))

    check(doesWin(listOf(1, 6, 11, 16, 21), listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 10),
        listOf(11, 12, 13, 14, 15),
        listOf(16, 17, 18, 19, 20),
        listOf(21, 22, 23, 24, 25)
    )))

    check(0 == score(listOf(0), listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 10),
        listOf(11, 12, 13, 14, 15),
        listOf(16, 17, 18, 19, 20),
        listOf(21, 22, 23, 24, 25)
    )))

    check((1..25).sum() - (1..5).sum() == score(listOf(1, 2, 3, 4, 5), listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 10),
        listOf(11, 12, 13, 14, 15),
        listOf(16, 17, 18, 19, 20),
        listOf(21, 22, 23, 24, 25)
    )))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}