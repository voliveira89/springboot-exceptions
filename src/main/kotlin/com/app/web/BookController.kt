package com.app.web

import com.app.domain.Book
import com.app.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/book")
class BookController(val bookService: BookService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Book>> =
        ResponseEntity.ok().body(bookService.getAll())

    @GetMapping("/{isbn}")
    fun get(@PathVariable isbn: String): ResponseEntity<Book> =
        ResponseEntity.ok().body(bookService.get(isbn))

    @PutMapping
    fun update(@RequestBody book: Book): ResponseEntity<Unit> {
        bookService.update(book)
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun create(@RequestBody book: Book): ResponseEntity<Unit> {
        bookService.create(book)
        return ResponseEntity.created(URI("/book/" + book.isbn)).build()
    }
}
