package com.example.stakanchik.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.stakanchik.databinding.FragmentMainBinding
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.base.BaseFragment
import com.example.stakanchik.ui.base.Event
import com.example.stakanchik.ui.main.rv.ArticleAdapter
import com.example.stakanchik.ui.main.rv.ArticleViewHolder
import com.example.stakanchik.ui.main.rv.HorizontalArticleViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: BaseFragment<MainArticlesViewModel, FragmentMainBinding> (
    MainArticlesViewModel::class.java,
    { FragmentMainBinding.inflate(it) }
), ArticleAdapter.Listener, OnClicked {

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
        vm.getArticle()


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

    override fun onClick(index: Int) {
//        vm.getArticleByIndex(index).let {
//            fragmentListener.onClickOpenFragment(ArticleDetailsFragment.newInstance(it.article_id))
//        }
    }
}