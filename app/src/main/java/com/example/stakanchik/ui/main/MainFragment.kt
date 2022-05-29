package com.example.stakanchik.ui.main

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stakanchik.R
import com.example.stakanchik.databinding.FragmentMainBinding
import com.example.stakanchik.extentions.toArticlesDto
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.article.ArticleDetailsFragment
import com.example.stakanchik.ui.base.BaseFragment
import com.example.stakanchik.ui.base.Event
import com.example.stakanchik.ui.favourite.FavouriteArticlesFragment
import com.example.stakanchik.ui.main.rv.ArticleAdapter
import com.example.stakanchik.ui.popular.PopularArticlesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Array.get
import java.util.concurrent.Executors

@AndroidEntryPoint
class MainFragment: BaseFragment<MainArticlesViewModel, FragmentMainBinding> (
    MainArticlesViewModel::class.java,
    { FragmentMainBinding.inflate(it) }
), ArticleAdapter.Listener {

    private lateinit var fragmentListener: OnClicked
    private val articleAdapter: ArticleAdapter = ArticleAdapter(this)
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as OnClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBar()
        setupViews()
        subscribeToLiveData()
        vm.getArticle()
    }

    private fun showBar() {
        val menu = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        menu.visibility = View.VISIBLE

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        toolbar.visibility = View.VISIBLE
    }

    private fun setupViews() {
        with(binding){
            recyclerView.adapter = articleAdapter
            linearLayoutManager = LinearLayoutManager(activity)
            recyclerView.layoutManager = linearLayoutManager
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

    override fun onClick(index: Int) {
        vm.article.value?.get(index)?.let {
            fragmentListener.onClickOpenFragment(ArticleDetailsFragment.newInstance(it.objectId))
            it.views += 1
            it.is_read = true
            Executors.newSingleThreadExecutor().execute {
                vm.updateArticleViewsAndIsRead(it.toArticlesDto())
                Handler(Looper.getMainLooper()).post {}
            }
        }
    }
}