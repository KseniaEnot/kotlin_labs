import task4_Matrix.Matrix
import task5_LibraryService.*
import java.time.Year

fun main() {
    println("Task4")
    testTask4()
    println("Task5")
    testTask5()
}

fun testTask5() {
    val library = Library()
    library.addBook(
        Book(
            "To Kill a Mockingbird",
            Genre.Gothic,
            Author("Lee", "Harper"),
            Year.of(1960),
            1
        )
    )
    library.addBook(
        Book(
            "It",
            Genre.Novel,
            Author("Stephen", "King"),
            Year.of(1986),
            2
        )
    )
    library.addBook(
        Book(
            "Dandelion Wine",
            Genre.Novel,
            Author("Ray", "Bradbury"),
            Year.of(1957),
            3
        )
    )
    library.addBook(
        Book(
            "Coraline",
            Genre.Horror,
            Author("Neil", "Gaiman"),
            Year.of(2002),
            4
        )
    )
    library.registerUser("Andrey", "Arhipov")
    library.registerUser("Nikolai", "Serov")
    library.registerUser("Veronika", "Grigoryan")
    library.unregisterUser(User("Nikolai", "Serov"))
    library.bookWillBePublished(
        Book(
            "Coraline",
            Genre.Horror,
            Author("Neil", "Gaiman"),
            Year.of(2002),
            4
        )
    )
    library.sendForRestoration(
        Book(
            "Dandelion Wine",
            Genre.Novel,
            Author("Ray", "Bradbury"),
            Year.of(1957),
            3
        )
    )
    library.setBookStatus(
        Book(
            "It",
            Genre.Novel,
            Author("Stephen", "King"),
            Year.of(1986),
            2
        ), Status.Available
    )
    library.takeBook(
        User("Andrey", "Arhipov"), Book(
            "It",
            Genre.Novel,
            Author("Stephen", "King"),
            Year.of(1986),
            2
        )
    )
    library.returnBook(
        Book(
            "Dandelion Wine",
            Genre.Novel,
            Author("Ray", "Bradbury"),
            Year.of(1957),
            3
        )
    )
    println("Books substring It")
    for (it in library.findBooks("It")) {
        println(it.toString())
    }
    println("Books Genre = Novel")
    for (it in library.findBooks(Genre.Novel)) {
        println(it.toString())
    }
    println("Available books")
    for (it in library.getAllAvailableBooks()) {
        println(it.toString())
    }
    println("Statuses books")
    for (it in library.getAllBookStatuses()) {
        println("""${it.key} ${it.value}""")
    }
}

fun testTask4() {
    val exampele1 = Matrix(arrayOf(arrayOf(2.0, 1.0), arrayOf(9.0, 3.0)))
    val exampele2 = Matrix(arrayOf(arrayOf(8.0, 4.0), arrayOf(5.0, 12.0)))
    print((exampele1 * exampele2).toString() + System.lineSeparator())
    exampele2 += 1.2
    print(exampele2.toString() + System.lineSeparator())
    exampele2 -= 1.2
    print(exampele2.toString() + System.lineSeparator())
    exampele2 *= 2.0
    print(exampele2.toString() + System.lineSeparator())
    exampele2 /= 1.2
    print(exampele2.toString() + System.lineSeparator())
    exampele2 += exampele1
    print(exampele2.toString() + System.lineSeparator())
    exampele2 -= exampele1
    print(exampele2.toString() + System.lineSeparator())
    exampele2 *= exampele1
    print(exampele2.toString() + System.lineSeparator())
    val exampele3 = exampele1 + exampele2 * exampele2 - exampele2
    print(exampele3.toString() + System.lineSeparator())
}