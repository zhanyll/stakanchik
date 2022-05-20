package com.example.stakanchik.ui.popular

import com.example.stakanchik.databinding.FragmentPopularArticlesBinding
import com.example.stakanchik.ui.base.BaseFragment

class PopularArticlesFragment: BaseFragment<PopularArticlesViewModel, FragmentPopularArticlesBinding>(
    PopularArticlesViewModel::class.java,
    { FragmentPopularArticlesBinding.inflate(it) }
) {

}