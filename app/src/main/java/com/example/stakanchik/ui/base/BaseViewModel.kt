package com.example.stakanchik.ui.base

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

    protected val disposable by lazy {
        CompositeDisposable()
    }

    @Inject
    lateinit var  resources: Resources

    protected val _event by lazy {
        MutableLiveData<BaseEvent>()
    }
    val event: LiveData<BaseEvent>
        get() = _event

    private val _isLoading by lazy {
        MutableLiveData(false)
    }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}