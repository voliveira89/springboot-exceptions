package com.app

import com.app.domain.Book
import com.app.repository.BookRepository
import com.app.service.BookService
import com.app.web.BookController
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@RunWith(SpringRunner::class)
@SpringBootTest
class BookControllerIT {

    @Autowired
    lateinit var bookService: BookService

    @Autowired
    lateinit var bookController: BookController

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var objectMapper: ObjectMapper

    lateinit var mockClient: MockMvc

    private lateinit var book: Book

    @Before
    fun setUp() {
        mockClient = MockMvcBuilders.standaloneSetup(bookController).build()

        book = bookService.save(Book("9789726081890", "Nineteen Eighty-Four",
            "George Orwell", 1, "Antígona", 314))
    }

    @After
    fun tearDown() {
        bookRepository.deleteAll()
    }

    @Test
    fun getBook() {
        assertThat(bookService.getAll().size, `is`(1))

        mockClient.perform(get("/book/" + book.isbn))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.isbn", `is`(book.isbn)))
            .andExpect(jsonPath("$.title", `is`(book.title)))
            .andExpect(jsonPath("$.author", `is`(book.author)))
    }

    @Test
    fun getAllBooks() {
        assertThat(bookService.getAll().size, `is`(1))

        val book2 = Book("9780141036137", "Animal Farm", "George Orwell",
            1, "Penguin Books", 112)

        bookService.save(book2)

        mockClient.perform(get("/book"))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize<Any>(2)))
            .andExpect(jsonPath("$[*].isbn", containsInAnyOrder(book.isbn, book2.isbn)))
            .andExpect(jsonPath("$[*].publisher",
                containsInAnyOrder(book.publisher, book2.publisher)))
            .andExpect(jsonPath("$[*].title", containsInAnyOrder(book.title, book2.title)))

        assertEquals(bookService.getAll().size, 2)
    }

    @Test
    fun createBook() {
        assertThat(bookService.getAll().size, `is`(1))

        val book2 = Book("9780141036137", "Animal Farm", "George Orwell",
            1, "Penguin Books", 112)

        mockClient.perform(post("/book")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(book2)))
            .andExpect(status().isCreated)

        assertEquals(bookService.getAll().size, 2)

        val bookAssert = bookService.get(book2.isbn)
        assertThat(book2.isbn, `is`(bookAssert.isbn))
        assertThat(book2.title, `is`(bookAssert.title))
        assertThat(book2.author, `is`(bookAssert.author))
        assertThat(book2.edition, `is`(bookAssert.edition))
        assertThat(book2.publisher, `is`(bookAssert.publisher))
        assertThat(book2.pages, `is`(bookAssert.pages))
    }
}
