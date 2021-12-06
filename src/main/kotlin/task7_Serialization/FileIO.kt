package task7_Serialization

import java.io.File

object FileIO {
    fun fileWriter(value: String, path: String) {
        File(path).writeText(value)
    }

    fun fileReader(path: String): String = File(path).readText()
}