package mtyy.martin.playground.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("articles")
    suspend fun listArticles(): ArticlesListResponse

    @GET("articles/{id}")
    suspend fun getArticle(@Path("id") id: Int): Article
}