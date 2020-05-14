package com.mahendra.covid.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {
    var navigator : WeakReference<N>? = null

    fun getNavigator() : N? {
        return navigator?.get()
    }

    fun setNavigator(n : N){
        navigator = WeakReference(n)
    }
}