package com.example.stakanchik.ui.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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
    }


    companion object {
        fun newInstance(id: Long): ArticleDetailsFragment {
            val args = Bundle().apply { putLong(Long::class.java.canonicalName, id) }
            return ArticleDetailsFragment().apply { arguments = args }
        }
    }
}