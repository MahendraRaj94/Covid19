package com.mahendra.covid.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.PieData
import com.google.android.material.snackbar.Snackbar
import com.jaredrummler.materialspinner.MaterialSpinner
import com.mahendra.covid.base.Constants
import com.mahendra.covid.databinding.ChartFragmentBinding
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.viewmodel.ChartViewModel
import com.mahendra.covid.viewmodel.ChartViewNavigator
import kotlinx.android.synthetic.main.chart_fragment.*


class ChartFragment : Fragment(),ChartViewNavigator {

    companion object {
        fun newInstance(countryData: CountryData?) : ChartFragment{
           val fragment =  ChartFragment()
            val bundle = Bundle();
               bundle.apply {
               putParcelable(Constants.KEY_COUNTRY_DATA,countryData)
           }
            fragment.arguments = bundle;
            return fragment;
        }
    }

    private lateinit var viewModel: ChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ChartFragmentBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)
        viewModel.setNavigator(this)
        viewModel.initViews(arguments)
    }


    fun setBarChart(generateData: BarData?) {
        pieChart.visibility = View.GONE
        barChart.visibility = View.VISIBLE
        barChart.data = generateData
        barChart.legend.textColor = Color.WHITE
        barChart.setDrawGridBackground(false)
        barChart.setFitBars(true)
        barChart.setGridBackgroundColor(Color.WHITE)
        barChart.setDrawValueAboveBar(true)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawLabels(false)
        barChart.xAxis.textColor = Color.WHITE
        barChart.getAxis(YAxis.AxisDependency.RIGHT).setDrawGridLines(false)
        barChart.getAxis(YAxis.AxisDependency.RIGHT).setDrawAxisLine(false)
        barChart.getAxis(YAxis.AxisDependency.RIGHT).setDrawLabels(false)

        barChart.getAxis(YAxis.AxisDependency.LEFT).setDrawGridLines(false)
        barChart.getAxis(YAxis.AxisDependency.LEFT).textColor = Color.WHITE

        barChart.description.isEnabled = false
    }

    fun setPieChart(generateData: PieData?) {
        pieChart.visibility = View.VISIBLE
        barChart.visibility = View.GONE
        pieChart.data = generateData
        pieChart.centerText = generateData?.dataSet?.label
        pieChart.setCenterTextColor(Color.BLACK)
        pieChart.setDrawEntryLabels(false)
        pieChart.setCenterTextSize(20f)
        pieChart.setEntryLabelTextSize(20f)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.legend.textColor = Color.WHITE
        pieChart.description.isEnabled = false
    }

    override fun initViews() {
        materialSpinner.setItems(getSpinnerItems())
        materialSpinner.setOnItemSelectedListener({ view, position, id, item ->
           when(getSpinnerItems()[position]){
               Constants.ChartTitle.BAR_CHART ->{
                   setBarChart(viewModel.getBarData())
               }
               Constants.ChartTitle.PIE_CHART ->{
                   setPieChart(viewModel.getPieData())

               }
               Constants.ChartTitle.LINE_CHART ->{

               }
               else ->{

           }
           }
        })

    }

    fun getSpinnerItems() : ArrayList<String>{
        val arrayList : ArrayList<String> = ArrayList();
        arrayList.add(Constants.ChartTitle.BAR_CHART)
        arrayList.add(Constants.ChartTitle.PIE_CHART)
        arrayList.add(Constants.ChartTitle.LINE_CHART)
        return arrayList
    }

    override fun setBarChart(countryData: CountryData?) {
        if(countryData != null) {
            setBarChart(viewModel.getBarData())
        }
    }

}
