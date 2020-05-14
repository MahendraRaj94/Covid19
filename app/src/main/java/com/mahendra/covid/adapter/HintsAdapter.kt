package com.mahendra.covid.adapter

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.mahendra.covid.databinding.ListItemChartBinding
import com.mahendra.covid.databinding.ListItemHintBinding
import com.mahendra.covid.model.CountryData


class HintsAdapter() : RecyclerView.Adapter<HintsAdapter.ViewHolder>() {
    lateinit var items : ArrayList<String>
    class ViewHolder(val binding: ListItemHintBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    constructor(items : ArrayList<String>) : this() {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemHintBinding.inflate(LayoutInflater.from(parent.context));
        val displayMetrics = parent.context.resources.displayMetrics;
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
//        return items.size
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    private fun setBarcharts(
        holder: ViewHolder,
        position: Int
    ) {
    }

    fun updateData(items: ArrayList<String>) {
        this.items = items
        notifyDataSetChanged();
    }

}