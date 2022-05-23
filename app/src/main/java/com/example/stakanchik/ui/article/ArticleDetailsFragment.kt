package com.example.stakanchik.ui.article

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.stakanchik.databinding.FragmentArticleDetailsBinding
import com.example.stakanchik.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment: BaseFragment<ArticleDetailsViewModel, FragmentArticleDetailsBinding> (
    ArticleDetailsViewModel::class.java,
    { FragmentArticleDetailsBinding.inflate(it) }
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragmentViews()
    }

    private fun setUpFragmentViews() {
        binding.run {
            vm.getArticleById(id)
            val article = vm.article
            articleTitle.text = article.value?.topic
            articleText.text = article.value?.text
            articleAuthor.text = article.value?.author
            view?.let { Glide.with(it).load(article.value?.image).into(articleImage) }
        }
    }

    companion object {
        fun newInstance(id: Int): ArticleDetailsFragment {
            val args = Bundle().apply { putInt(Int::class.java.canonicalName, id) }
            return ArticleDetailsFragment().apply { arguments = args }
        }
    }
}