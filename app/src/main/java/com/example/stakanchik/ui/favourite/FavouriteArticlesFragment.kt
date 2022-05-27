package com.example.stakanchik.ui.favourite

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stakanchik.R
import com.example.stakanchik.databinding.FragmentFavouriteArticlesBinding
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.article.ArticleDetailsFragment
import com.example.stakanchik.ui.base.BaseFragment
import com.example.stakanchik.ui.base.Event
import com.example.stakanchik.ui.main.MainFragment
import com.example.stakanchik.ui.main.rv.ArticleAdapter
import com.example.stakanchik.ui.popular.PopularArticlesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteArticlesFragment: BaseFragment<FavouriteArticlesViewModel, FragmentFavouriteArticlesBinding>(
    FavouriteArticlesViewModel::class.java,
    { FragmentFavouriteArticlesBinding.inflate(it) }
), ArticleAdapter.Listener {

    private lateinit var fragmentListener: OnClicked
    private val articleAdapter: ArticleAdapter = ArticleAdapter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as OnClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        subscribeToLiveData()
        vm.getFavouriteArticles()
//        binding.bottomNav.selectedItemId = R.id.menu_starred
//        binding.run{
//            bottomNav.setOnItemSelectedListener {
//                onItemSelected(it)
//            }
//        }
    }

    private fun setupViews() {
        with(binding){
            recyclerView.adapter = articleAdapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
            swipeRefresh.setOnRefreshListener {
                vm.loadArticles()
            }

            GridLayoutManager(activity, RecyclerView.VERTICAL)
                .apply { recyclerView.layoutManager = this }
        }
    }

    private fun subscribeToLiveData(){
        vm.article.observe(viewLifecycleOwner) {
            articleAdapter.setNewItems(it)
        }

        vm.event.observe(requireActivity()) {
            when (it) {
                is Event.ShowLoading -> binding.swipeRefresh.isRefreshing = true
                is Event.StopLoading -> binding.swipeRefresh.isRefreshing = false
            }
        }
    }

//    private fun onItemSelected(it: MenuItem) = when (it.itemId) {
//        R.id.menu_home -> {
//            fragmentListener.onClickOpenFragment(MainFragment())
//            true
//        }
//        R.id.menu_popular -> {
//            fragmentListener.onClickOpenFragment(PopularArticlesFragment())
//            true
//        }
//        R.id.menu_starred -> {
//            fragmentListener.onClickOpenFragment(FavouriteArticlesFragment())
//            true
//        }
//        R.id.menu_user -> {
//            //
//            true
//        }
//        else -> false
//    }

    override fun onClick(index: Int) {
        vm.article.value?.get(index)?.let {
            fragmentListener.onClickOpenFragment(ArticleDetailsFragment.newInstance(it.objectId))
        }
    }
}