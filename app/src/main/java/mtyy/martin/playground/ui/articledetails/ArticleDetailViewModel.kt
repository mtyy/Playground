package mtyy.martin.playground.ui.articledetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mtyy.martin.playground.data.NewsRepository
import mtyy.martin.playground.data.network.Article
import mtyy.martin.playground.di.DispatcherIo
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    // could also use Dispatchers.Default if id do more logic here, or perhaps define both dispatchers.
    @DispatcherIo private val dispatcherIo: CoroutineDispatcher) : ViewModel() {

    private val _articleFlow: MutableStateFlow<Article> = MutableStateFlow(Article(-1, "Loading")) //TODO wrapper for loading state
    val articleFlow: StateFlow<Article> = _articleFlow

    // ViewModel should create coroutines, and not expose suspend functions
    // https://developer.android.com/kotlin/coroutines/coroutines-best-practices#viewmodel-coroutines
    fun loadArticle(articleId: Int) {
        viewModelScope.launch {
            withContext(dispatcherIo) {
                _articleFlow.value = newsRepository.getArticle(articleId)
            }
        }
    }




}