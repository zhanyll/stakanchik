package com.example.stakanchik.domain.models

data class Article(
    val objectId: String,
    val topic: String,
    val text: String,
    val publish_date: String,
    val author: String,
    val genre: String, ///need to fix
    val is_marked: Boolean,
    val is_read: Boolean,
    val image: String,
    val views: Int
)
