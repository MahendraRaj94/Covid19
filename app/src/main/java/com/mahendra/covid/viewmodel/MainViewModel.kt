package com.mahendra.covid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahendra.covid.base.BaseViewModel
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.repository.GetCountrywiseDataRepository

class MainViewModel : BaseViewModel<MainViewNavigator>() {
    
    var countryData : MutableLiveData<ArrayList<CountryData>>? = MutableLiveData<ArrayList<CountryData>>();
    private var repository : GetCountrywiseDataRepository? = null
    fun initViews() {
        getNavigator()?.initViews();
        repository = GetCountrywiseDataRepository.getInstance();
        countryData = repository?.getCountrywiseData()
    }

    fun getData(fromRefresh : Boolean) : MutableLiveData<ArrayList<CountryData>>?{
        if(fromRefresh){
            countryData = repository?.getCountrywiseData()
        }
        return countryData;
    }
}
