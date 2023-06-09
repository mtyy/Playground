package mtyy.martin.playground.ui.articlelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import mtyy.martin.playground.ui.MainActivityViewModel
import mtyy.martin.playground.ui.theme.PlaygroundTheme

@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    companion object {
        val TAG = ArticleListFragment::class.simpleName
    }

    private val viewModel: ArticleListViewModel by viewModels()

    private val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val articles by viewModel.articlesFlow.collectAsStateWithLifecycle()
                PlaygroundTheme() {
                    ArticleList(articles = articles, onClick = { id -> activityViewModel.onArticleClicked(id) } )
                }
            }
        }
    }
}