package com.app

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@SpringBootApplication
open class ExceptionsSpringApplication {

    fun main(args: Array<String>) {
        SpringApplication.run(ExceptionsSpringApplication::class.java, *args)
    }

    @Bean
    @Primary
    open fun objectMapper() = ObjectMapper().apply {
        registerModule(KotlinModule())
    }
}
