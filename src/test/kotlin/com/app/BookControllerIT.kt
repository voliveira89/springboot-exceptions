package com.app

import com.app.domain.Book
import com.app.service.BookService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class BookControllerIT {

    @Autowired
    lateinit var bookService: BookService

    @Before
    fun setUp() {
        val book = Book("9789726081890", "Nineteen Eighty-Four",
            "George Orwell", 1, "Antígona", 314)

        bookService.save(book)
    }

    @Test
    fun get() {
        assertEquals(bookService.getAll().size, 1)

        //TODO finish test
    }

}
