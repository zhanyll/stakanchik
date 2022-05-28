package com.example.stakanchik.ui.read

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stakanchik.R
import com.example.stakanchik.databinding.FragmentReadArticlesBinding
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.article.ArticleDetailsFragment
import com.example.stakanchik.ui.base.BaseFragment
import com.example.stakanchik.ui.base.Event
import com.example.stakanchik.ui.main.rv.ArticleAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadArticlesFragment: BaseFragment<ReadArticlesViewModel, FragmentReadArticlesBinding>(
    ReadArticlesViewModel::class.java,
    { FragmentReadArticlesBinding.inflate(it) }
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
        vm.getArticle()
        hideBar()
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

    private fun hideBar() {
        val menu = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        menu.visibility = View.GONE

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        toolbar.visibility = View.GONE
    }

    override fun onClick(index: Int) {
        vm.article.value?.get(index)?.let {
            fragmentListener.onClickOpenFragment(ArticleDetailsFragment.newInstance(it.objectId))
        }
    }
}