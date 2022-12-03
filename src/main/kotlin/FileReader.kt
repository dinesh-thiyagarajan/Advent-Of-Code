class FileReader(private val fileName: String) {
    fun getRawData(): List<String> {
        val data = object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
        if (data.isNullOrEmpty()) {
            return listOf()
        }
        return data
    }
}