package com.mahendra.covid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var title: LiveData<String>? = MutableLiveData<String>("Hello Mahendra");
}