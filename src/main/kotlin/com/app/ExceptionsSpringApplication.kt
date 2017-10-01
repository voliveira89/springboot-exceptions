package com.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ExceptionsSpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(ExceptionsSpringApplication::class.java, *args)
}
