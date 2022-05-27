package com.example.stakanchik.ui.article

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.example.stakanchik.R
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.databinding.FragmentArticleDetailsBinding
import com.example.stakanchik.ui.base.BaseFragment
import com.example.stakanchik.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment: BaseFragment<ArticleDetailsViewModel, FragmentArticleDetailsBinding> (
    ArticleDetailsViewModel::class.java,
    { FragmentArticleDetailsBinding.inflate(it) }
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArguments()
        subscribeToLiveData()
    }

    private fun parseArguments() {
        arguments?.getString(String::class.java.canonicalName)?.let {
            vm.getArticleById(it)
        }
    }

    private fun subscribeToLiveData() {
        vm.article.observe(viewLifecycleOwner) {
            it?.let { setUpFragmentViews(it) }
        }
    }

    private fun setUpFragmentViews(article: ArticlesEntity) {
        binding.run {
            articleTitle.text = article.topic
            articleText.text = article.text
            articleAuthor.text = article.author
            view?.let { Glide.with(it).load(article.image).into(articleImage) }
        }
    }

    companion object {
        fun newInstance(objectId: String): ArticleDetailsFragment {
            val args = Bundle().apply { putString(String::class.java.canonicalName, objectId) }
            return ArticleDetailsFragment().apply { arguments = args }
        }
    }
}