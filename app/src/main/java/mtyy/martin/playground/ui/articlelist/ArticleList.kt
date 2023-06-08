package mtyy.martin.playground.ui.articlelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mtyy.martin.playground.data.network.Article

@Composable
fun ArticleList(articles: List<Article>, onClick: (id: Int) -> Unit) {
    Column {
        articles.forEach { article ->
            Row(modifier = Modifier.padding(bottom = 10.dp).clickable { onClick(article.id) }) {
                Text(text = article.title)
            }

        }
    }
}

@Preview
@Composable
fun previewArticles() {
    val articles = listOf(
        Article(1, "Title1"),
        Article(2, "Title2"),
        Article(3, "Title3"),
        Article(4, "Title4"),
        Article(5, "Title5"),
    )
    ArticleList(articles = articles, onClick = {})
}