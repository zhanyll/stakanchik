package com.example.stakanchik.ui.article

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.stakanchik.R
import com.example.stakanchik.data.models.ArticlesEntity
import com.example.stakanchik.databinding.FragmentArticleDetailsBinding
import com.example.stakanchik.domain.models.Article
import com.example.stakanchik.extentions.toArticleEntity
import com.example.stakanchik.extentions.toArticlesDto
import com.example.stakanchik.ui.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors

@AndroidEntryPoint
class ArticleDetailsFragment: BaseFragment<ArticleDetailsViewModel, FragmentArticleDetailsBinding> (
    ArticleDetailsViewModel::class.java,
    { FragmentArticleDetailsBinding.inflate(it) }
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArguments()
        subscribeToLiveData()
        hideBar()
    }

    private fun hideBar() {
        val menu = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
        menu.visibility = View.GONE

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        toolbar.visibility = View.GONE
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

    private fun setUpFragmentViews(article: Article) {
        binding.run {
            articleTitle.text = article.topic
            articleText.text = article.text
            articleAuthor.text = article.author
            articleGenre.text = article.genre
            articleViewsCount.text = article.views.toString()
            view?.let { Glide.with(it).load(article.image).into(articleImage) }
            if (article.is_marked) {
                markButton.setBackgroundResource(R.drawable.unmarkedbutton)
            } else {
                markButton.setBackgroundResource(R.drawable.markedbutton)
            }

            markButton.setOnClickListener {
                if (article.is_marked) {
                    article.is_marked = false
                    Executors.newSingleThreadExecutor().execute {
                        vm.updateIsMarked(article.toArticlesDto())
                        Handler(Looper.getMainLooper()).post {}
                    }
                    markButton.setBackgroundResource(R.drawable.markedbutton)

                } else {
                    article.is_marked = true
                    Executors.newSingleThreadExecutor().execute {
                        vm.updateIsMarked(article.toArticlesDto())
                        Handler(Looper.getMainLooper()).post {}
                    }
                    markButton.setBackgroundResource(R.drawable.unmarkedbutton)
                }
            }
        }
    }

    companion object {
        fun newInstance(objectId: String): ArticleDetailsFragment {
            val args = Bundle().apply { putString(String::class.java.canonicalName, objectId) }
            return ArticleDetailsFragment().apply { arguments = args }
        }
    }
}