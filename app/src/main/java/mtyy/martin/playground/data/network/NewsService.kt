package mtyy.martin.playground.data.network

import retrofit2.http.GET

interface NewsService {

    @GET("articles")
    suspend fun listArticles(): ArticlesResponse
}