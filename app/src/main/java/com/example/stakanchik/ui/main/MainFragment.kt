package com.example.stakanchik.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.stakanchik.databinding.FragmentMainBinding
import com.example.stakanchik.extentions.showToast
import com.example.stakanchik.ui.OnClicked
import com.example.stakanchik.ui.article.ArticleDetailsFragment
import com.example.stakanchik.ui.base.BaseEvent
import com.example.stakanchik.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: BaseFragment<MainViewModel, FragmentMainBinding> (
    MainViewModel::class.java,
    { FragmentMainBinding.inflate(it) }
), OnClicked {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
    }

    private fun subscribeToLiveData(){
        vm.article.observe(
            requireActivity(),
        ) {
            showToast(it.toString())
        }

        vm.event.observe(requireActivity()) {
            when (it) {
                is BaseEvent.ShowToast -> showToast(it.message)
            }
        }
    }

//    private fun setupViews() {
//        binding.run {
//            button.setOnClickListener {
//                Log.d("get", "success")
//                vm.getArticle()
//            }
//        }
//    }
}