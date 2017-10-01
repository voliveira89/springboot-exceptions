package com.app.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "books")
data class Book(@Id val isbn: String,
                @Field var title: String,
                @Field var author: String,
                @Field var edition: Int,
                @Field var publisher: String,
                @Field var pages: Int)
