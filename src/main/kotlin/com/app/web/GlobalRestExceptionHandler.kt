package com.app.web

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
    fun notFoundHandler(): ResponseEntity<Unit> =
        ResponseEntity.notFound().build()

    @ExceptionHandler(ConflictException::class)
    fun conflictHandler(): ResponseEntity<Unit> =
        ResponseEntity.badRequest().build()

    @ExceptionHandler(Exception::class)
    fun globalExceptionHhandler(): ResponseEntity<Unit> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
}