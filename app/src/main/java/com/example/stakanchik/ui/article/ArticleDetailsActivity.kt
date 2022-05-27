//package com.example.stakanchik.ui.article
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.AttributeSet
//import android.view.View
//import com.bumptech.glide.Glide
//import com.example.stakanchik.R
//import com.example.stakanchik.data.models.ArticlesEntity
//import com.example.stakanchik.databinding.ActivityArticleDetailsBinding
//import com.example.stakanchik.ui.OnClicked
//import com.example.stakanchik.ui.base.BaseActivity
//import com.example.stakanchik.ui.main.MainFragment
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ArticleDetailsActivity: BaseActivity<ArticleDetailsViewModel, ActivityArticleDetailsBinding>(
//    ArticleDetailsViewModel::class.java,
//    { ActivityArticleDetailsBinding.inflate(it) }
//), OnClicked {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val view = binding.root
//        parseArguments()
//        subscribeToLiveData(view)
//    }
//
//    private fun parseArguments() {
////        arguments?.getString(String::class.java.canonicalName)?.let {
////            vm.getArticleById(it)
////        }
//        intent.getStringExtra(String::class.java.canonicalName)?.let {
//            vm.getArticleById(it)
//        }
//    }
//
//    private fun subscribeToLiveData(view: View) {
//        vm.article.observe(this) {
//            it?.let { setUpFragmentViews(view, it) }
//        }
//    }
//
//    private fun setUpFragmentViews(view: View, article: ArticlesEntity) {
//        binding.run {
//            articleTitle.text = article.topic
//            articleText.text = article.text
//            articleAuthor.text = article.author
//            view.let { Glide.with(it).load(article.image).into(articleImage) }
//        }
//    }
//
//    fun newInstance(objectId: String, intent: Intent): ArticleDetailsActivity {
////            val args = Bundle().apply { putString(String::class.java.canonicalName, objectId) }
//        intent.putExtra(String::class.java.canonicalName, objectId)
//        return ArticleDetailsActivity()
//    }
//
////    companion object {
////        fun newInstance(objectId: String, intent: Intent): ArticleDetailsActivity {
//////            val args = Bundle().apply { putString(String::class.java.canonicalName, objectId) }
////            intent.putExtra(String::class.java.canonicalName, objectId)
////            return ArticleDetailsActivity()
////        }
////    }
//}