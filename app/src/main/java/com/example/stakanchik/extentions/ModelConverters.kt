package com.example.stakanchik.extentions

import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.stakanchik.data.models.ArticlesDto
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.domain.models.Article

fun ArticlesDto.toArticleEntity(): ArticlesEntity {
    return ArticlesEntity(
        objectId,
        topic,
        text,
        publish_date,
        author,
        genre,
        is_marked,
        is_read,
        image,
        views
    )
}

fun ArticlesDto.toArticle(): Article {
    return Article(
        objectId,
        topic,
        text,
        publish_date,
        author,
        genre,
        is_marked,
        is_read,
        image,
        views
    )
}

fun Article.toArticlesDto(): ArticlesDto {
    return ArticlesDto(
        objectId,
        topic,
        text,
        publish_date,
        author,
        genre,
        is_marked,
        is_read,
        image,
        views
    )
}

fun ArticlesEntity.toArticle(): Article {
    return Article(
        objectId,
        topic,
        text,
        publish_date,
        author,
        genre,
        is_marked,
        is_read,
        image,
        views
    )
}

fun Article.toArticleEntity(): ArticlesEntity {
    return ArticlesEntity(
        objectId,
        topic,
        text,
        publish_date,
        author,
        genre,
        is_marked,
        is_read,
        image,
        views
    )
}