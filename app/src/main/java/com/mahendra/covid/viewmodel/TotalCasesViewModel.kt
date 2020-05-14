package com.mahendra.covid.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.mahendra.covid.base.BaseViewModel
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.repository.GetCountrywiseDataRepository
import com.mahendra.covid.repository.GetGlobalDataRepository
import com.mahendra.covid.repository.GetIndiaDataRepository

class TotalCasesViewModel : BaseViewModel<TotalCasesNavigator>() {

    private var countryData: MutableLiveData<CountryData>? = null
    private var repository : GetGlobalDataRepository? = null

    private var indiaData: MutableLiveData<CountryData>? = null
    private var indiaRepository : GetIndiaDataRepository? = null

    fun init(){
        repository = GetGlobalDataRepository.getInstance();
        indiaRepository = GetIndiaDataRepository.getInstance();
        countryData = repository?.getGlobalData()
        indiaData = indiaRepository?.getIndiaData();
        getNavigator()?.initViews();

    }
     fun getBarData(countryData: CountryData): BarData? {
         val colors : ArrayList<Int> = ArrayList();
        val entries = java.util.ArrayList<BarEntry>()
         var pos : Float = 1f;
         if(countryData.confirmedCases != 0L) {
             entries.add(BarEntry(pos, countryData.confirmedCases * 1f, "Total"))
             colors.add(ColorTemplate.COVID_COLORS.get(0))
             pos = pos + 1f;
         }
         if(countryData.activeCases != 0L) {
             entries.add(BarEntry(pos, countryData.activeCases * 1f, "Active"))
             colors.add(ColorTemplate.COVID_COLORS.get(1))
             pos = pos + 1f;
         }else{
             countryData.activeCases = countryData.confirmedCases - countryData.serious - countryData.deceased - countryData.recovered
             entries.add(BarEntry(pos, countryData.activeCases * 1f, "Active"))
             colors.add(ColorTemplate.COVID_COLORS.get(1))
             pos = pos + 1f;
         }
         if(countryData.deceased != 0L) {
             entries.add(BarEntry(pos, countryData.deceased * 1f, "Death"))
             colors.add(ColorTemplate.COVID_COLORS.get(2))
             pos = pos + 1f;
         }
         if(countryData.serious != 0L) {
             entries.add(BarEntry(pos, countryData.serious * 1f, "Serious"))
             colors.add(ColorTemplate.COVID_COLORS.get(3))
             pos = pos + 1f;
         }
         if(countryData.recovered != 0L) {
             entries.add(BarEntry(pos, countryData.recovered * 1f, "Recovered"))
             colors.add(ColorTemplate.COVID_COLORS.get(4))
             pos = pos + 1f;
         }

        val d = BarDataSet(entries, "${countryData.countryName}")
        d.setColors(colors)
        d.valueTextColor = Color.WHITE
        val sets = java.util.ArrayList<IBarDataSet>()
        sets.add(d)
        val cd = BarData(sets)
        cd.setValueTextColor(Color.WHITE)
        return cd
    }

    fun getGlobalTotalData() : MutableLiveData<CountryData>?{
        return countryData;
    }

    fun getIndiaTotalData() : MutableLiveData<CountryData>?{
        return indiaData;
    }
}
