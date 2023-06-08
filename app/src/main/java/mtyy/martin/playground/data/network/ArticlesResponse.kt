package mtyy.martin.playground.data.network

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse (
    val results: List<Article>
)

@Serializable
data class Article(
    val title: String
)