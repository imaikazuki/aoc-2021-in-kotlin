fun main() {
    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.toCharArray().toList() }
            .mapIndexed { row, it ->
                it.mapIndexed { col, c ->
                    val l = mutableListOf<Char>()
                    if( 0 < row ) l.add(input[row-1][col])
                    if( col < input[row].length - 1 ) l.add(input[row][col + 1])
                    if( row < input.size -1 ) l.add(input[row+1][col])
                    if( 0 < col ) l.add(input[row][col-1])
                    Pair (c, l.toList())
                }
            }
            .flatten()
            .filter { datum -> datum.second.all { x -> datum.first < x } }
            .map { it.first.digitToInt() + 1 }
            .sumOf { it }
    }

    fun trackBasin(input: List<String>, basins: MutableList<MutableList<Int>>, row: Int, col: Int, id: Int) {
        basins[row][col] = id
        if( 0 < row && '9' != input[row-1][col] && 0 == basins[row-1][col]) trackBasin(input, basins, row-1, col, id)
        if( col < input[row].length-1 && '9' != input[row][col+1] && 0 == basins[row][col+1]) trackBasin(input, basins, row, col+1, id)
        if( row < input.size-1 && '9' != input[row+1][col] && 0 == basins[row+1][col]) trackBasin(input, basins, row+1, col, id)
        if( 0 < col && '9' != input[row][col-1] && 0 == basins[row][col-1]) trackBasin(input, basins, row, col-1, id)
    }

    fun part2(input: List<String>): Int {
        val basins = MutableList(input.size) { MutableList(input[0].length) { 0 } }
        var basinIdCounter = 1
        input
            .map { it.toCharArray() }
            .forEachIndexed { row, it ->
                it.forEachIndexed { col, c ->
                    if( '9' != c ) {
                        if(0 == basins[row][col]) trackBasin(input, basins, row, col, basinIdCounter)
                        basinIdCounter++
                    }
                }
            }
        val basinList = MutableList(basinIdCounter) { 0 }
        basins
            .flatten()
            .forEach { basinList[it]++ }
        return basinList.sorted().reversed().subList(1, 4).fold(1) { acc, i -> acc * i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
