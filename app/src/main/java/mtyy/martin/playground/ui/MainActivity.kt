package mtyy.martin.playground.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import mtyy.martin.playground.R
import mtyy.martin.playground.ui.articlelist.ArticleListFragment

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivty_main)
        supportFragmentManager.beginTransaction().add(R.id.root, ArticleListFragment(), ArticleListFragment::class.simpleName).commit()
    }
}
