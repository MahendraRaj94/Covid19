package com.mahendra.covid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahendra.covid.base.BaseViewModel
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.model.CountryItemData
import com.mahendra.covid.network.GetIndiaStateDataResponse
import com.mahendra.covid.repository.GetCountrywiseDataRepository
import com.mahendra.covid.repository.GetIndiaStatewiseDataRepository

class IndianCasesViewModel :  BaseViewModel<IndiaStatewiseNavigator>() {

    var countryData : MutableLiveData<ArrayList<CountryItemData>>? = MutableLiveData<ArrayList<CountryItemData>>();
    private var repository : GetIndiaStatewiseDataRepository? = null
    fun initViews() {
        getNavigator()?.initViews();
        repository = GetIndiaStatewiseDataRepository.getInstance();
        countryData = repository?.getIndiaStatewiseData()
    }

    fun getData(fromRefresh : Boolean) : MutableLiveData<ArrayList<CountryItemData>>?{
        if(fromRefresh){
            countryData = repository?.getIndiaStatewiseData()
        }
        return countryData;
    }}