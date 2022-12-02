fun main() {
    val rawData = object {}.javaClass.getResourceAsStream("calorieCounter.txt")?.bufferedReader()?.readLines()
    if (rawData.isNullOrEmpty()) {
        print("No data found")
        return
    }
    rawData.map { it.trim() }
    val totalValues = arrayListOf<Int>()
    var currentIterationValue = 0
    repeat(rawData.size) {
        if (rawData[it].isEmpty()) {
            totalValues.add(currentIterationValue)
            currentIterationValue = 0
        } else {
            currentIterationValue += rawData[it].toInt()
        }
    }
    totalValues.sortDescending()
    println("Top three calories ${totalValues[0]} ${totalValues[1]} ${totalValues[2]}")
    println("Sum of top three calories ${totalValues[0] + totalValues[1] + totalValues[2]}")
}
