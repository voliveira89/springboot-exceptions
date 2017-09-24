package com.app.service

import com.app.domain.Book
import com.app.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(val bookRepository: BookRepository) {

    fun get(isbn: String): Book = bookRepository.findOne(isbn)

    fun getAll(): List<Book> = bookRepository.findAll()

    fun save(book: Book): Book = bookRepository.save(book)
}
