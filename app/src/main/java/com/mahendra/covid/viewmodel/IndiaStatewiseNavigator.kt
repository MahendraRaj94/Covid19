package com.mahendra.covid.viewmodel

import com.github.mikephil.charting.data.BarData
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.model.CountryItemData

interface IndiaStatewiseNavigator {
    fun initViews();
    fun setData(items : ArrayList<CountryItemData>)
}
