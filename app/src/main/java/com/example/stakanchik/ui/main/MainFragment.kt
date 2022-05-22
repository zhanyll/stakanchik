package com.example.stakanchik.ui.main

import android.os.Bundle
import android.view.View
import com.example.stakanchik.databinding.FragmentMainBinding
import com.example.stakanchik.extentions.showToast
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseFragment
import com.example.stakanchik.ui.main.rv.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: BaseFragment<MainArticlesViewModel, FragmentMainBinding> (
    MainArticlesViewModel::class.java,
    { FragmentMainBinding.inflate(it) }
), ArticleAdapter.Listener, OnClicked {

//    private lateinit var fragmentListener: OnClicked
    private val articleAdapter: ArticleAdapter = ArticleAdapter(this)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()

        vm.getArticle()
    }

    private fun subscribeToLiveData(){
        vm.article.observe(requireActivity()) {
            articleAdapter.setNewItems(it)
            showToast(it.toString())
        }

        vm.event.observe(requireActivity()) {
            when (it) {
                is BaseEvent.ShowToast -> showToast(it.message)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onClick(index: Int) {
        vm.getArticleByIndex(index)?.let {
//            fragmentListener.onMain(ArticleDetailsFragment.newInstance(it.article_id))
        }
    }
}