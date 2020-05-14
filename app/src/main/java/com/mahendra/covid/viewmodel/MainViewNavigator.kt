package com.mahendra.covid.viewmodel

import com.mahendra.covid.model.CountryData

interface MainViewNavigator {
    fun initViews();
    fun setData(items : ArrayList<CountryData>)
}
