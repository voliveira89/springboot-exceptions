package com.app.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "books")
data class Book(@Id val isbn: String,
                val name: String,
                val author: String,
                val edition: Int,
                val publisher: String,
                val nPages: Int)