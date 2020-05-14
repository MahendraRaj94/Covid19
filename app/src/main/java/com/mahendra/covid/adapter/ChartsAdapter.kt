package com.mahendra.covid.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.mahendra.covid.base.Constants
import com.mahendra.covid.databinding.ListItemChartBinding
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.model.CountryItemData
import java.text.DecimalFormat


class ChartsAdapter(private var items: ArrayList<CountryData>) :
    RecyclerView.Adapter<ChartsAdapter.ViewHolder>(),Filterable {
    val numberFormat: DecimalFormat = DecimalFormat("##,##,##,###")
    private var filteredItems : List<CountryData> = items;

    class ViewHolder(val binding: ListItemChartBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemChartBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        setBarcharts(holder, position)
        holder.binding.pieChart.data = generateDataPie(filteredItems.get(position))
        setPiechartUI(holder.binding.pieChart, filteredItems.get(position))

    }

    private fun setPiechartUI(pieChart: PieChart, countryData: CountryData) {
        val description = Description()
        description.text = "Covid-19 data"
        pieChart.description = description
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.description.textColor = Color.WHITE
        pieChart.isHighlightPerTapEnabled = true;
        pieChart.centerText = countryData.countryName;
        pieChart.setCenterTextColor(Color.BLACK)
        pieChart.setDrawSlicesUnderHole(true)
        pieChart.setUsePercentValues(false)
        pieChart.setDrawEntryLabels(false)
        pieChart.data.setValueFormatter(PercentFormatter(pieChart, true))
        // chart.spin(2000, 0, 360);
        val l: Legend = pieChart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.textColor = Color.WHITE
        l.setDrawInside(false)
    }

    fun updateData(items: ArrayList<CountryData>) {
        this.items = items.filter {
            !it.countryName.equals("World",true)
        } as ArrayList<CountryData>

        this.filteredItems = this.items;
        notifyDataSetChanged();
    }

    private fun generateDataPie(countryData: CountryData): PieData? {
        val entries = java.util.ArrayList<PieEntry>()
        entries.add(
            PieEntry(
                (countryData.confirmedCases - countryData.activeCases - countryData.serious - countryData.deceased)* 1f
                , "Total ".plus("(${numberFormat.format(countryData.confirmedCases)})")
            )
        )
        entries.add(
            PieEntry(
                countryData.activeCases * 1f
                , "Active ".plus("(${numberFormat.format(countryData.activeCases)})")
            )
        )
        entries.add(
            PieEntry(
                countryData.deceased * 1f
                , "Death ".plus("(${numberFormat.format(countryData.deceased)})")
            )
        )
        entries.add(
            PieEntry(
                countryData.recovered * 1f
                , "Recovered ".plus("(${numberFormat.format(countryData.recovered)})")
            )
        )
        entries.add(
            PieEntry(
                countryData.serious * 1f
                , "Serious ".plus("(${numberFormat.format(countryData.serious)})")
            )
        )
        val d = PieDataSet(entries, "")
        // space between slices
        d.sliceSpace = 2f
        d.selectionShift = 5f;
        d.setDrawValues(false)
        d.setColors(*ColorTemplate.COVID_COLORS)
        //dataSet.setSelectionShift(0f);
//        d.setValueLinePart1OffsetPercentage(80f)
//        d.isValueLineVariableLength = true;
        d.valueTextSize = 10f;
        d.valueTextColor = Color.WHITE
//        d.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE)
        return PieData(d)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var data : List<CountryData> = ArrayList();
                if(p0 == null || p0.length == 0){
                    data = items;
                }else{
                    data = items.filter { item ->
                        item.countryName != null && item.countryName!!.contains(p0,true)
                    }
                }
                var results : FilterResults = FilterResults();
                results.values = data;
                return results;
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                p1?.let {
                    filteredItems = it.values as List<CountryData>;
                    notifyDataSetChanged()
                }

            }

        }
    }

    fun sortItems(sortType: Constants.SortType) {
        when(sortType){
            Constants.SortType.SET_TO_DEFAULT ->{
                items.sortBy {
                    it.countryName
                }
            }
            Constants.SortType.SERIOUS_UP ->{
                items.sortByDescending {
                    it.serious
                }
            }
            Constants.SortType.SERIOUS_DOWN ->{
                items.sortBy {
                    it.serious
                }
            }
            Constants.SortType.RECOVERED_UP ->{
                items.sortByDescending {
                    it.recovered
                }
            }
            Constants.SortType.RECOVERED_DOWN ->{
                items.sortBy {
                    it.recovered
                }
            }
            Constants.SortType.DEATH_DOWN ->{
                items.sortBy {
                    it.deceased
                }
            }
            Constants.SortType.DEATH_UP ->{
                items.sortByDescending {
                    it.deceased
                }
            }
            Constants.SortType.ACTIVE_DOWN ->{
                items.sortBy {
                    it.activeCases
                }
            }
            Constants.SortType.ACTIVE_UP ->{
                items.sortByDescending {
                    it.activeCases
                }
            }
        }
        notifyDataSetChanged()
    }

}