package com.app.service

import com.app.domain.Book
import com.app.exception.ConflictException
import com.app.exception.NotFoundException
import com.app.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(val bookRepository: BookRepository) {

    fun get(isbn: String): Book = bookRepository.findOne(isbn) ?: throw NotFoundException("ISBN $isbn not found!")

    fun getAll(): List<Book> = bookRepository.findAll()

    fun create(book: Book): Book {
        bookRepository.findOne(book.isbn)
            ?.let { throw ConflictException("ISBN $book.isbn already exists!") }
            ?: return save(book)
    }

    fun update(book: Book) {
        bookRepository.findOne(book.isbn)
            ?.let { save(book) }
            ?: throw NotFoundException("ISBN $book.isbn not found!")
    }

    fun save(book: Book): Book = bookRepository.save(book)
}
