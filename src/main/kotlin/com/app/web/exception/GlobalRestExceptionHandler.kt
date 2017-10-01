package com.app.web.exception

import com.app.exception.ConflictException
import com.app.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Created by e.Near on 2017
 */
@ControllerAdvice
class GlobalRestExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundHandler(ex: NotFoundException): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)

    @ExceptionHandler(ConflictException::class)
    fun conflictHandler(ex: ConflictException): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)

    @ExceptionHandler(Exception::class)
    fun globalExceptionHhandler(): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Aw snap! Something went wrong!")
}