package com.example.stakanchik.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.stakanchik.R
import com.example.stakanchik.databinding.ActivityMainBinding
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.article.ArticleDetailsFragment
import com.example.stakanchik.ui.base.BaseActivity
import com.example.stakanchik.ui.favourite.FavouriteArticlesFragment
import com.example.stakanchik.ui.popular.PopularArticlesFragment
import com.example.stakanchik.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class.java,
    { ActivityMainBinding.inflate(it) }
), OnClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
            onClickOpenFragment(MainFragment(),false)
            binding.bottomNav.selectedItemId = R.id.menu_home
        }

        binding.run{
            bottomNav.setOnItemSelectedListener {
                onItemSelected(it)
            }
        }
    }

    fun visibilityOfBottom(is_visible: Int){
        if (is_visible == 0) {
            visibilityOfBottom(View.VISIBLE)
        }
        else if (is_visible == 8) {
            visibilityOfBottom(View.GONE)
        }
    }

    override fun onClickOpenFragment(fragment: Fragment, addToBackStack: Boolean?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment).apply {
                if(addToBackStack == true){
                    addToBackStack("")
                }
            }
            .commit()
    }

//    override fun onClickOpenArticle(activity: Activity, addToBackStack: Boolean?) {
//        val intent = Intent()
//        startActivity(intent)
//    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.getStringExtra(String::class.java.canonicalName)?.let {
            onClickOpenFragment(ArticleDetailsFragment.newInstance(it))
        }
    }

    private fun onItemSelected(it: MenuItem) = when (it.itemId) {
        R.id.menu_home -> {
            onClickOpenFragment(MainFragment())
            true
        }
        R.id.menu_popular -> {
            onClickOpenFragment(PopularArticlesFragment())
            true
        }
        R.id.menu_starred -> {
            onClickOpenFragment(FavouriteArticlesFragment())
            true
        }
        R.id.menu_user -> {
            onClickOpenFragment(ProfileFragment())
            true
        }
        else -> false
    }
}