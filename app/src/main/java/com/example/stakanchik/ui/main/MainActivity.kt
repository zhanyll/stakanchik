package com.example.stakanchik.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.stakanchik.R
import com.example.stakanchik.databinding.ActivityMainBinding
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.article.ArticleDetailsFragment
import com.example.stakanchik.ui.base.BaseActivity
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.popular.PopularArticlesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class.java,
    { ActivityMainBinding.inflate(it) }
), OnClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
//            onMain(MainFragment(),false)
            binding.bottomNav.selectedItemId = R.id.menu_home
        }

        binding.run{
            bottomNav.setOnItemSelectedListener {
                onItemSelected(it)
            }
        }
    }

    override fun onMain(fragment: Fragment, addToBackStack: Boolean?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment).apply {
                if(addToBackStack == true){
                    addToBackStack("")
                }
            }
            .commit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.getStringExtra("CHARACTER_ID")?.let {
            onMain(ArticleDetailsFragment.newInstance(it.toLong()))
        }
    }

    private fun onItemSelected(it: MenuItem) = when (it.itemId) {
        R.id.menu_home -> {
            onMain(MainFragment())
            true
        }
        R.id.menu_popular -> {
//            fragmentListener.onMain(PopularArticlesFragment())
            onMain(PopularArticlesFragment())
            true
        }
        R.id.menu_starred -> {
            //
            true
        }
        R.id.menu_user -> {
            //
            true
        }
        else -> false
    }
}