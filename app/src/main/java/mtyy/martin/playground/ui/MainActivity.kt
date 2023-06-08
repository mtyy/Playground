package mtyy.martin.playground.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mtyy.martin.playground.R
import mtyy.martin.playground.ui.articledetails.ArticleDetailFragment
import mtyy.martin.playground.ui.articlelist.ArticleListFragment
import mtyy.martin.playground.util.Event

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivty_main)

        // https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
        // You can also use the Flow.flowWithLifecycle operator when you have only one flow to collect
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.showArticleFlow.collect { event ->
                    handleNavigationEvent(event)
                }
            }
        }
    }

    private fun handleNavigationEvent(event: Event<Int>) {
        event.getContentIfNotHandled()?.let { id ->
            if (id == MainActivityViewModel.NOT_SHOWING_DETAILS) {
                if (supportFragmentManager.findFragmentByTag(ArticleListFragment.TAG) == null) {
                    supportFragmentManager.commit {
                        add(R.id.root, ArticleListFragment(), ArticleListFragment.TAG)
                    }
                }
            } else {
                if (supportFragmentManager.findFragmentByTag(ArticleDetailFragment.TAG) == null) {
                    supportFragmentManager.commit {
                        replace(R.id.root, ArticleDetailFragment.newInstance(id))
                        addToBackStack(ArticleDetailFragment.TAG)
                    }
                }
            }
        }
    }
}
