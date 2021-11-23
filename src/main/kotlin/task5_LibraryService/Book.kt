package task5_LibraryService

import java.time.Year

enum class Genre {
    Novel,
    Gothic,
    Horror;
}

data class Book(
    val name: String,
    val genre: Genre,
    val author: Author,
    val yearRelease: Year,
    val bookId: Int
)