package mtyy.martin.playground.ui.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import mtyy.martin.playground.ui.theme.PlaygroundTheme

@AndroidEntryPoint
class ArticleDetailFragment : Fragment() {

    private val viewModel: ArticleDetailViewModel by viewModels()

    companion object {
        val TAG = ArticleDetailFragment::class.simpleName
        private const val ARTICLE_ID = "ARTICLE_ID"

        fun newInstance(articleId: Int): ArticleDetailFragment {
            val args = Bundle()
            args.putInt(ARTICLE_ID, articleId)
            val fragment = ArticleDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadArticle(requireArguments().getInt(ARTICLE_ID, -1))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val article by viewModel.articleFlow.collectAsStateWithLifecycle()
                PlaygroundTheme() {
                    ArticleDetail(article = article)
                }
            }
        }
    }

}