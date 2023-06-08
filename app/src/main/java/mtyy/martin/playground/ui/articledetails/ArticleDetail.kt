package mtyy.martin.playground.ui.articledetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mtyy.martin.playground.data.network.Article

@Composable
fun ArticleDetail(article: Article) {
    Column {
        Text(style = MaterialTheme.typography.headlineLarge, text = article.title)
        Text(text = article.summary)
    }
}

@Preview
@Composable
fun previewArticles() {
    val article = Article(id = 1, title = "Horse landed on the moon", summary = "After great efforts by our glorious leaders we are finally ready to plow the fields of moon to prepare for the collapse of our habitat")
    ArticleDetail(article = article)
}