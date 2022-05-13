package com.example.stakanchik.domain.models

import java.util.*

data class Article(
    val article_id: Long,
    val topic: String,
    val text: String,
    val publish_date: Date,
    val author: String,
    val genre: String, ///need to fix
    val is_marked: Boolean,
    val is_read: Boolean
)
