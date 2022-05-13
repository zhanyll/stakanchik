package com.example.stakanchik.extentions

import com.example.stakanchik.data.models.ArticlesDto
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.domain.models.Article

fun ArticlesDto.toArticleEntity(): ArticlesEntity {
    return ArticlesEntity(
        article_id,
        topic,
        text,
        publish_date,
        author,
        genre,
        is_marked,
        is_read
    )
}

fun ArticlesDto.toArticle(): Article {
    return Article(
        article_id,
        topic,
        text,
        publish_date,
        author,
        genre,
        is_marked,
        is_read
    )
}