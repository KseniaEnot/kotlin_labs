package task5_LibraryService

import java.time.Year
import org.apache.log4j.*


sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()

    override fun toString(): String {
        return when (this) {
            Available -> "Available"
            ComingSoon -> "ComingSoon"
            Restoration -> "Restoration"
            is UsedBy -> "UsedBy " + this.user.toString()
        }
    }

}

interface LibraryService {
    fun findBooks(substring: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: Year): List<Book>
    fun findBooks(genre: Genre): List<Book>
    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>
    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>
    fun setBookStatus(book: Book, status: Status)
    fun addBook(book: Book, status: Status = Status.Available)
    fun registerUser(name: String, surname: String)
    fun unregisterUser(user: User)
    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
    fun sendForRestoration(book: Book)
    fun bookWillBePublished(book: Book)
}

class Library : LibraryService {
    private var users = listOf<User>()
    private var mutBookUsers = mutableMapOf<Book, User>()
    private val mutBookStatus = mutableMapOf<Book, Status>()
    private val logger = Logger.getLogger("Library created")

    override fun registerUser(name: String, surname: String) {
        if (users.find { it.name == name && it.surname == surname } != null) {
            logger.error("Trying to create an existing user $name $surname")
            throw IllegalStateException("User already exists")
        }
        logger.info("Successful user creation $name $surname")
        users = users + User(name, surname)
    }

    override fun unregisterUser(user: User) {
        if (users.isEmpty()) {
            logger.error("User list is Empty")
            throw IndexOutOfBoundsException("User list is Empty")
        }
        val deletedUser = users.find { it == user }
        if (deletedUser == null) {
            logger.error("Attempt to delete a non-existent user ${user.name} ${user.surname}")
            throw IllegalStateException("User does not exist")
        }
        logger.info("Successful user creation ${deletedUser.name} ${deletedUser.surname}")
        users = users - deletedUser
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (status is Status.UsedBy) {
            if (users.isEmpty()) {
                logger.error("User list is Empty")
                throw IndexOutOfBoundsException("User list is Empty")
            }
            if (users.find { it == status.user } == null) {
                logger.error("An attempt to set the status to a non-existing user ${status.user.name} ${status.user.surname}")
                throw IllegalStateException("User does not exists")
            }
        }
        if (mutBookStatus.isEmpty()) {
            logger.error("Book list is Empty")
            throw IndexOutOfBoundsException("Book does not exists")
        }
        if (mutBookStatus.keys.find { it == book } == null) {
            logger.error("Attempting to set the status of a non-existent book ${book.bookId} ${book.name}")
            throw IllegalStateException("Book does not exists")
        }
        logger.info("Successful status setting")
        mutBookStatus[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        if (status is Status.UsedBy) {
            if (users.find { it == status.user } == null) {
                logger.error("An attempt to set the status to a non-existing user ${status.user.name} ${status.user.surname}")
                throw IllegalStateException("User does not exists")
            }
        }
        if (mutBookStatus.keys.find { it == book } != null) {
            logger.error("Trying to create an existing book ${book.bookId} ${book.name}")
            throw IllegalStateException("Book already exists")
        } else {
            logger.info("Successful book creation ${book.bookId} ${book.name}")
            mutBookStatus[book] = status
        }
    }

    override fun takeBook(user: User, book: Book) {
        if (users.isEmpty() || mutBookStatus.isEmpty()) {
            logger.error("Book or user list is Empty")
            throw IndexOutOfBoundsException("Book or user list is Empty")
        }
        if (mutBookStatus.keys.find { it == book } == null || users.find { it == user } == null) {
            logger.error("Book lending is not possible.")
            throw IllegalStateException("Book or user does not exists")
        }
        if (mutBookStatus[book] != Status.Available) {
            logger.error("Book lending is not possible.Book not available ${mutBookStatus[book].toString()}")
            throw IllegalStateException("Book not available")
        }
        if (mutBookUsers.filter { it.value == user }.count() >= 3) {
            logger.error("Book lending is not possible.User has too many books ${user.name} ${user.surname}")
            throw IllegalStateException("User has too many books")
        }
        logger.info("Successful return of the book ${book.bookId} ${book.name}")
        mutBookUsers[book] = user
        mutBookStatus[book] = Status.UsedBy(user)
    }

    override fun sendForRestoration(book: Book) {
        if (mutBookStatus.isEmpty()) {
            logger.error("Book list is Empty")
            throw IndexOutOfBoundsException("Book does not exists")
        }
        if (mutBookStatus.keys.find { it == book } == null) {
            logger.error("Book restoration is not possible.Book does not exists")
            throw IllegalStateException("Book does not exists")
        }
        if (mutBookStatus[book] != Status.Available) {
            logger.error("Book lending is not possible.Book not available ${mutBookStatus[book].toString()}")
            throw IllegalStateException("Book not available")
        }
        mutBookStatus[book] = Status.Restoration
    }

    override fun bookWillBePublished(book: Book) {
        if (mutBookStatus.isEmpty()) {
            logger.error("Book list is Empty")
            throw IndexOutOfBoundsException("Book does not exists")
        }
        if (mutBookStatus.keys.find { it == book } == null) {
            logger.error("Book publish is not possible.Book does not exists")
            throw IllegalStateException("Book does not exists")
        }
        if (mutBookStatus[book] != Status.Available) {
            logger.error("Book lending is not possible.Book not available ${mutBookStatus[book].toString()}")
            throw IllegalStateException("Book not available")
        }
        mutBookStatus[book] = Status.ComingSoon
    }

    override fun returnBook(book: Book) {
        val usedBook = mutBookStatus.keys.find { it == book }
        if (usedBook == null) {
            logger.error("Book return is not possible.Book does not exists")
            throw IllegalStateException("Book does not exists")
        }
        if (mutBookStatus[usedBook] == Status.Available) {
            logger.error("Book return is not possible.Book already available ${mutBookStatus[book].toString()}")
            throw IllegalStateException("The book already available")
        }
        if (mutBookUsers[book] != null)
            mutBookUsers = mutBookUsers.minus(book) as MutableMap<Book, User>
        mutBookStatus[usedBook] = Status.Available
    }

    override fun findBooks(substring: String): List<Book> {
        if (mutBookStatus.keys.none { it.name.contains(substring) })
            logger.warn("No matches found substring = $substring")
        return mutBookStatus.keys.filter { it.name.contains(substring) }
    }

    override fun findBooks(author: Author): List<Book> {
        if (mutBookStatus.keys.none { it.author == author })
            logger.warn("No matches found author = $author")
        return mutBookStatus.keys.filter { it.author == author }
    }

    override fun findBooks(year: Year): List<Book> {
        if (mutBookStatus.keys.none { it.yearRelease == year })
            logger.warn("No matches found year = $year")
        return mutBookStatus.keys.filter { it.yearRelease == year }
    }

    override fun findBooks(genre: Genre): List<Book> {
        if (mutBookStatus.keys.none { it.genre == genre })
            logger.warn("No matches found genre = $genre")
        return mutBookStatus.keys.filter { it.genre == genre }
    }

    override fun getAllBooks(): List<Book> {
        if (mutBookStatus.isEmpty())
            logger.warn("Referring to an empty list")
        return mutBookStatus.keys.toList()
    }

    override fun getAllAvailableBooks(): List<Book> {
        if (mutBookStatus.filter { it.value == Status.Available }.isEmpty()) {
            logger.warn("No books available")
        }
        return mutBookStatus.filter { it.value == Status.Available }.keys.toList()
    }

    override fun getBookStatus(book: Book): Status {
        if (mutBookStatus.keys.find { it == book } == null) {
            logger.warn("Book does not exists")
            throw IllegalArgumentException("Book does not exists")
        } else
            return mutBookStatus[book]!!
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        if (mutBookStatus.isEmpty())
            logger.warn("Referring to an empty list")
        return mutBookStatus
    }
}