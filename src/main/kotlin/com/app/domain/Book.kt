package com.app.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "books")
data class Book(@Id val isbn: String,
                @Field val title: String,
                @Field val author: String,
                @Field val edition: Int,
                @Field val publisher: String,
                @Field val pages: Int)
