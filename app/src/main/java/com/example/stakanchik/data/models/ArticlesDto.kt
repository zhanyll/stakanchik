package com.example.stakanchik.data.models

data class ArticlesDto(
    val objectId: String,
    val topic: String,
    val text: String,
    val publish_date: String,
    val author: String,
    val genre: String, ///need to fix
    val is_marked: Boolean,
    var is_read: Boolean,
    val image: String,
    var views: Int
)
