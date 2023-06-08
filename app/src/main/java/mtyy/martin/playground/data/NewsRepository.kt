package mtyy.martin.playground.data

import mtyy.martin.playground.data.network.Article
import mtyy.martin.playground.data.network.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getArticles(): List<Article> {
        return newsService.listArticles().results
    }

    suspend fun getArticle(id: Int): Article {
        return newsService.getArticle(id)
    }
}