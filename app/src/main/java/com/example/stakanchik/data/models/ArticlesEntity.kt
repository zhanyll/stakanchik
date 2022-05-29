package com.example.stakanchik.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ArticlesEntity(
    @PrimaryKey()
    val objectId: String,
    val topic: String,
    val text: String,
    val publish_date: String,
    val author: String?,
    val genre: String?, ///need to fix
    var is_marked: Boolean,
    var is_read: Boolean,
    val image: String,
    var views: Int
)
