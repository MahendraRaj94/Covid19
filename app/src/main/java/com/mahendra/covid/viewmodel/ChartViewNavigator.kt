package com.mahendra.covid.viewmodel

import com.mahendra.covid.model.CountryData

interface ChartViewNavigator {
    fun initViews();
    fun setBarChart(countryData: CountryData?)
}
