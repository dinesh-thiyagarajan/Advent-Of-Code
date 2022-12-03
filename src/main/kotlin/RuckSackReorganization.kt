fun main() {
    val rawData = FileReader("ruckSackReorganization.txt").getRawData()
    if (rawData.isEmpty()) {
        print("No data found")
        return
    }

    println(part1(rawData = rawData))
    println(part2(rawData = rawData))
}


fun part1(rawData: List<String>): Int {
    return rawData
        .map { Pair(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
        .sumOf { compartments ->
            compartments.first.toCharArray()
                .find { compartments.second.contains(it) }!!
                .getCharacterValue()
        }
}

fun part2(rawData: List<String>): Int {
    return rawData.chunked(3)
        .sumOf { compartment ->
            compartment[0].toCharArray()
                .find { compartment[1].contains(it) && compartment[2].contains(it) }!!
                .getCharacterValue()
        }
}

fun Char.getCharacterValue() = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(this) + 1