package com.mahendra.covid.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import com.mahendra.covid.R
import com.mahendra.covid.activty.IndianCasesActivity
import com.mahendra.covid.databinding.TotalCasesFragmentBinding
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.viewmodel.TotalCasesNavigator
import com.mahendra.covid.viewmodel.TotalCasesViewModel
import kotlinx.android.synthetic.main.total_cases_fragment.*
import java.text.DecimalFormat

class TotalCasesFragment : Fragment(),TotalCasesNavigator {

    companion object {
        fun newInstance() = TotalCasesFragment()
    }

    private lateinit var viewModel: TotalCasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return TotalCasesFragmentBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TotalCasesViewModel::class.java)
        viewModel.setNavigator(this)
        viewModel.init()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun initViews() {
//        shimmerLayout.visibility = View.VISIBLE
        viewModel.getGlobalTotalData()?.observe(this,object : Observer<CountryData>{
            override fun onChanged(t: CountryData?) {
                if(t == null){
                    
                }else{
                    setGlobalBarData(viewModel.getBarData(t))
                    setGlobalData(t)
//                    shimmerLayout.visibility = View.GONE
                }
            }

        })
        viewModel.getIndiaTotalData()?.observe(this,object : Observer<CountryData>{
            override fun onChanged(t: CountryData?) {
                if(t == null){

                }else{
                    setIndiaBarData(viewModel.getBarData(t))
                    setIndiaData(t)
//                    shimmerLayout.visibility = View.GONE
                }
            }

        })
        fl_button.setOnClickListener {
            expandable_layout.toggle()
            if(expandable_layout.isExpanded)
                iv_see_all_countries.rotation = 180f
            else
                iv_see_all_countries.rotation = 0f

        }
        fl_india_button.setOnClickListener {
            expandable_layout_india.toggle()
            if(expandable_layout_india.isExpanded)
                iv_see_all_states.rotation = 180f
            else
                iv_see_all_states.rotation = 0f

        }
        tv_cases_in_india.setOnClickListener {
            val intent = Intent(context,IndianCasesActivity::class.java);
            startActivity(intent);
        }
//        val hintsAdapter = HintsAdapter(ArrayList());
//        rv_hints.layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false);
//        rv_hints.adapter = hintsAdapter
    }

    private fun setGlobalData(countryData: CountryData) {
        card_view_parent.visibility = View.VISIBLE
        val decimalFormat = DecimalFormat("##,##,##,##,##0");

        countryData.confirmedCases?.toInt()?.let {
            if(it == 0){
                tv_header_totalcases.visibility = View.GONE
                tv_value_totalcases.visibility = View.GONE
            }else {
                tv_header_totalcases.visibility = View.VISIBLE
                tv_value_totalcases.visibility = View.VISIBLE
                tv_value_totalcases
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
        countryData.deceased?.toInt()?.let {
            if (it == 0) {
                tv_header_deceased.visibility = View.GONE
                tv_value_deceased.visibility = View.GONE
            } else {
                tv_header_deceased.visibility = View.VISIBLE
                tv_value_deceased.visibility = View.VISIBLE
                tv_value_deceased
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
        countryData.recovered?.toInt()?.let {
            if (it == 0) {
                tv_header_recovered.visibility = View.GONE
                tv_value_recovered.visibility = View.GONE
            } else {
                tv_header_recovered.visibility = View.VISIBLE
                tv_value_recovered.visibility = View.VISIBLE
                tv_value_recovered
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
        countryData.serious?.toInt()?.let {
            if (it == 0) {
                tv_header_serious.visibility = View.GONE
                tv_value_serious.visibility = View.GONE
            } else {
                tv_header_serious.visibility = View.VISIBLE
                tv_value_serious.visibility = View.VISIBLE
                tv_value_serious
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }

        countryData.activeCases?.toInt()?.let {
            if (it == 0) {
                tv_header_activecases.visibility = View.GONE
                tv_value_activecases.visibility = View.GONE
            } else {
                tv_header_activecases.visibility = View.VISIBLE
                tv_value_activecases.visibility = View.VISIBLE
                tv_value_activecases
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
    }

    private fun setIndiaData(countryData: CountryData) {
        card_view_parent.visibility = View.VISIBLE
        val decimalFormat = DecimalFormat("##,##,##,##,##0");

        countryData.confirmedCases?.toInt()?.let {
            if (it == 0) {
                tv_header_india_totalcases.visibility = View.GONE
                tv_value_india_totalcases.visibility = View.GONE
            } else {
                tv_header_india_totalcases.visibility = View.VISIBLE
                tv_value_india_totalcases.visibility = View.VISIBLE
                tv_value_india_totalcases
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
        countryData.deceased?.toInt()?.let {
            if (it == 0) {
                tv_header_india_deceased.visibility = View.GONE
                tv_value_india_deceased.visibility = View.GONE
            } else {
                tv_header_india_deceased.visibility = View.VISIBLE
                tv_value_india_deceased.visibility = View.VISIBLE
                tv_value_india_deceased
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
        countryData.recovered?.toInt()?.let {
            if (it == 0) {
                tv_header_india_recovered.visibility = View.GONE
                tv_value_india_recovered.visibility = View.GONE
            } else {
                tv_header_india_recovered.visibility = View.VISIBLE
                tv_value_india_recovered.visibility = View.VISIBLE
                tv_value_india_recovered
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
        countryData.serious?.toInt()?.let {
            if (it == 0) {
                tv_header_india_serious.visibility = View.GONE
                tv_value_india_serious.visibility = View.GONE
            } else {
                tv_header_india_serious.visibility = View.VISIBLE
                tv_value_india_serious.visibility = View.VISIBLE
                tv_value_india_serious
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
        countryData.activeCases?.toInt()?.let {
            if (it == 0) {
                tv_header_india_activecases.visibility = View.GONE
                tv_value_india_activecases.visibility = View.GONE
            } else {
                tv_header_india_activecases.visibility = View.VISIBLE
                tv_value_india_activecases.visibility = View.VISIBLE
                tv_value_india_activecases
                    .setDecimalFormat(decimalFormat)
                    .setAnimationDuration(1000)
                    .countAnimation(it / 2, it)
            }
        }
    }


    private fun setGlobalBarData(generateData: BarData?) {
        bar_chart_global_cardview.visibility = View.VISIBLE
        bar_chart_global.data = generateData
        bar_chart_global.legend.textColor = Color.WHITE
        bar_chart_global.setDrawGridBackground(false)
        bar_chart_global.setFitBars(true)
        bar_chart_global.setGridBackgroundColor(Color.WHITE)
        bar_chart_global.setDrawValueAboveBar(true)
        bar_chart_global.xAxis.setDrawAxisLine(false)
        bar_chart_global.xAxis.setDrawGridLines(false)
        bar_chart_global.xAxis.setDrawLabels(false)
        bar_chart_global.xAxis.textColor = Color.WHITE
        bar_chart_global.getAxis(YAxis.AxisDependency.RIGHT).setDrawGridLines(false)
        bar_chart_global.getAxis(YAxis.AxisDependency.RIGHT).setDrawAxisLine(false)
        bar_chart_global.getAxis(YAxis.AxisDependency.RIGHT).setDrawLabels(false)

        bar_chart_global.getAxis(YAxis.AxisDependency.LEFT).setDrawGridLines(false)
        bar_chart_global.getAxis(YAxis.AxisDependency.LEFT).textColor = Color.WHITE

        bar_chart_global.description.isEnabled = false
        val onValueSelectedRectF = RectF()

        bar_chart_global.setOnChartValueSelectedListener(object : OnChartValueSelectedListener{
            override fun onNothingSelected() {
            }

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (e == null) return

                val bounds: RectF = onValueSelectedRectF
                bar_chart_global.getBarBounds(e as BarEntry?, bounds)
                val position: MPPointF = bar_chart_global.getPosition(e, AxisDependency.LEFT)
                MPPointF.recycleInstance(position)

            }
        })

        bar_chart_global.animateY(300)

        bar_chart_global.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,ChartFragment.newInstance(viewModel.getGlobalTotalData()?.value),ChartFragment::class.java.name)
                .addToBackStack(ChartFragment::class.java.name)
                .commitAllowingStateLoss()
        }
    }
    private fun setIndiaBarData(generateData: BarData?) {
        bar_chart_india_cardview.visibility = View.VISIBLE
        bar_chart_india.data = generateData
        bar_chart_india.legend.textColor = Color.WHITE
        bar_chart_india.setDrawGridBackground(false)
        bar_chart_india.setFitBars(true)
        bar_chart_india.setGridBackgroundColor(Color.WHITE)
        bar_chart_india.setDrawValueAboveBar(true)
        bar_chart_india.xAxis.setDrawAxisLine(false)
        bar_chart_india.xAxis.setDrawGridLines(false)
        bar_chart_india.xAxis.setDrawLabels(false)
        bar_chart_india.xAxis.textColor = Color.WHITE
        bar_chart_india.getAxis(YAxis.AxisDependency.RIGHT).setDrawGridLines(false)
        bar_chart_india.getAxis(YAxis.AxisDependency.RIGHT).setDrawAxisLine(false)
        bar_chart_india.getAxis(YAxis.AxisDependency.RIGHT).setDrawLabels(false)

        bar_chart_india.getAxis(YAxis.AxisDependency.LEFT).setDrawGridLines(false)
        bar_chart_india.getAxis(YAxis.AxisDependency.LEFT).textColor = Color.WHITE

        bar_chart_india.description.isEnabled = false
        val onValueSelectedRectF = RectF()

        bar_chart_india.setOnChartValueSelectedListener(object : OnChartValueSelectedListener{
            override fun onNothingSelected() {
            }

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (e == null) return

                val bounds: RectF = onValueSelectedRectF
                bar_chart_india.getBarBounds(e as BarEntry?, bounds)
                val position: MPPointF = bar_chart_india.getPosition(e, AxisDependency.LEFT)
                MPPointF.recycleInstance(position)

            }
        })

        bar_chart_india.animateY(300)

        bar_chart_india.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,ChartFragment.newInstance(viewModel.getGlobalTotalData()?.value),ChartFragment::class.java.name)
                .addToBackStack(ChartFragment::class.java.name)
                .commitAllowingStateLoss()
        }
    }

}
