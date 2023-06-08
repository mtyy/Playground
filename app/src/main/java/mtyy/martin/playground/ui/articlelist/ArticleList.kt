package mtyy.martin.playground.ui.articlelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mtyy.martin.playground.data.network.Article

@Composable
fun ArticleList(articles: List<Article>) {
    Column {
        articles.forEach { article ->
            Row {
                Text(text = article.title)
            }

        }
    }
}

@Preview
@Composable
fun previewArticles() {
    val articles = listOf(
        Article("Title1"),
        Article("Title2"),
        Article("Title3"),
        Article("Title4"),
        Article("Title5"),
    )
    ArticleList(articles = articles)
}