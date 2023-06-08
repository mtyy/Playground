package mtyy.martin.playground.data.network

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesListResponse (
    val results: List<Article>
)

@Serializable
data class Article(
    val id: Int,
    val title: String,
    val summary: String = ""
)