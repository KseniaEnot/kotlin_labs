package task5_LibraryService

class Author(
    val name: String,
    val surname: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Author

        if (name != other.name) return false
        if (surname != other.surname) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + surname.hashCode()
        return result
    }

    override fun toString(): String {
        return "Author='$name' '$surname'"
    }
}