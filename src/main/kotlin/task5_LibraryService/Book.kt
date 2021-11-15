package task5_LibraryService

import java.time.Year

enum class Genre {
    Novel,
    Gothic,
    Horror;
}

class Book(
    val name: String,
    val genre: Genre,
    val author: Author,
    val yearRelease: Year,
    val bookId: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (name != other.name) return false
        if (genre != other.genre) return false
        if (author != other.author) return false
        if (yearRelease != other.yearRelease) return false
        if (bookId != other.bookId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + yearRelease.hashCode()
        result = 31 * result + bookId
        return result
    }

    override fun toString(): String {
        return """Book(name='$name', genre=$genre, ${this.author}, yearRelease=$yearRelease, bookId=$bookId)"""
    }
}