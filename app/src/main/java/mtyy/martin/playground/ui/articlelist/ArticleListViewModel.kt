package mtyy.martin.playground.ui.articlelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mtyy.martin.playground.data.NewsRepository
import mtyy.martin.playground.data.network.Article
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {

    private val _articlesFlow: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val articlesFlow: StateFlow<List<Article>> = _articlesFlow

    init {
        viewModelScope.launch {
            _articlesFlow.value = newsRepository.getArticles()
        }
    }
}