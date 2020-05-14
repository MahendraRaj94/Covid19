package com.mahendra.covid.viewmodel

import android.graphics.Color
import android.os.Bundle
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.mahendra.covid.base.BaseViewModel
import com.mahendra.covid.base.Constants
import com.mahendra.covid.model.CountryData
import java.util.*

class ChartViewModel() : BaseViewModel<ChartViewNavigator>() {
    var countryData : CountryData? = null

    fun initViews(bundle: Bundle?){
        countryData = bundle?.getParcelable(Constants.KEY_COUNTRY_DATA)
        getNavigator()?.initViews()
        if(countryData != null){
            getNavigator()?.setBarChart(countryData)
        }
    }

    fun getBarData(): BarData? {
        countryData?.let {
            val entries = java.util.ArrayList<BarEntry>()

            if (it.confirmedCases != null) {
                entries.add(BarEntry(1f, it.confirmedCases!!.toInt() * 1f, "Confirmed"))
            }
            if (it.deceased != null) {
                entries.add(BarEntry(2f, it.deceased!!.toInt() * 1f, "Death"))
            }
            if (it.serious != null) {
                entries.add(BarEntry(3f, it.serious!!.toInt() * 1f, "Serious"))
            }
            if (it.recovered != null) {
                entries.add(BarEntry(4f, it.recovered!!.toInt() * 1f, "Recovered"))
            }
            val d = BarDataSet(entries, "${it.countryName}")
            d.setColors(*ColorTemplate.COVID_COLORS)
            d.valueTextColor = Color.WHITE
            val sets = java.util.ArrayList<IBarDataSet>()
            sets.add(d)
            val cd = BarData(sets)
            cd.setValueTextColor(Color.WHITE)
            return cd
        }
        return null
    }

    fun generateDataLine(cnt: Int): LineData? {
        val values1 =
            ArrayList<Entry>()
        for (i in 0..11) {
            values1.add(
                Entry(
                    i.toFloat(),
                    ((Math.random() * 65).toInt() + 40).toFloat()
                )
            )
        }
        val d1 = LineDataSet(values1, "New DataSet $cnt, (1)")
        d1.lineWidth = 2.5f
        d1.circleRadius = 4.5f
        d1.highLightColor = Color.rgb(244, 117, 117)
        d1.setDrawValues(false)
        val values2 =
            ArrayList<Entry>()
        for (i in 0..11) {
            values2.add(Entry(i.toFloat(), values1[i].y - 30))
        }
        val d2 = LineDataSet(values2, "New DataSet $cnt, (2)")
        d2.lineWidth = 2.5f
        d2.circleRadius = 4.5f
        d2.highLightColor = Color.rgb(244, 117, 117)
        d2.color = ColorTemplate.VORDIPLOM_COLORS[0]
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0])
        d2.setDrawValues(false)
        val sets = ArrayList<ILineDataSet>()
        sets.add(d1)
        sets.add(d2)
        return LineData(sets)
    }

    fun getPieData(): PieData? {
        val entries = ArrayList<PieEntry>()
        countryData?.let {
            if(it.confirmedCases != null) {
                entries.add(PieEntry(it.confirmedCases!!.toInt() * 1f, Constants.CasesTitle.CONFIRMED))
            }
            if(it.deceased != null) {
                entries.add(PieEntry(it.deceased!!.toInt() * 1f, Constants.CasesTitle.DECEASED))
            }
            if(it.serious != null) {
                entries.add(PieEntry(it.serious!!.toInt() * 1f, Constants.CasesTitle.SERIOUS))
            }
            if(it.recovered != null) {
                entries.add(PieEntry(it.recovered!!.toInt() * 1f, Constants.CasesTitle.RECOVERED))
            }
        }
        val d = PieDataSet(entries, countryData?.countryName)

        // space between slices
        d.sliceSpace = 2f
        d.valueTextSize =  14f
        d.setColors(*ColorTemplate.COVID_COLORS)
        return PieData(d)
    }
}
