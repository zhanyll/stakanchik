package com.example.stakanchik.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

open class BaseActivity<viewModel: BaseViewModel, viewBinding: ViewBinding>(
    private val vmClass: Class<viewModel>,
    inline val bindingCreator: (LayoutInflater) -> viewBinding
): AppCompatActivity() {

    protected lateinit var binding: viewBinding
    protected lateinit var vm: viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingCreator(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this)[vmClass]
    }

    private fun showLoading() {}

    private fun hideLoading() {}
}