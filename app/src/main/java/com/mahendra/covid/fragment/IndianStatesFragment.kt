package com.mahendra.covid.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahendra.covid.R
import com.mahendra.covid.adapter.CountryItemChartsAdapter
import com.mahendra.covid.base.Constants
import com.mahendra.covid.databinding.FragmentIndianStatesBinding
import com.mahendra.covid.model.CountryItemData
import com.mahendra.covid.viewmodel.IndiaStatewiseNavigator
import com.mahendra.covid.viewmodel.IndianCasesViewModel
import kotlinx.android.synthetic.main.main_fragment.*


class IndianStatesFragment : Fragment() , IndiaStatewiseNavigator,
    FilterFragment.SortItemClickListener {

    companion object {
        fun newInstance() = IndianStatesFragment()
    }

    private var mClicktime: Long = 0;
    private var sortType : Constants.SortType = Constants.SortType.SET_TO_DEFAULT
    private lateinit var viewModel: IndianCasesViewModel
    private var chartsAdapter : CountryItemChartsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentIndianStatesBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_indian_states,menu)
        val searchItem = menu.findItem(R.id.menu_search)

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView.queryHint = "Enter state"
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    chartsAdapter?.filter?.filter(p0)
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(IndianCasesViewModel::class.java)
        viewModel.setNavigator(this)
        viewModel.initViews();
        viewModel.getData(false)?.observe(viewLifecycleOwner,object :
            Observer<ArrayList<CountryItemData>> {
            override fun onChanged(t: ArrayList<CountryItemData>?) {
                if(t != null){
                    chartsAdapter?.updateData(t);
                }
            }
        })
    }

    override fun initViews() {
        rv_charts.layoutManager = LinearLayoutManager(context)
        chartsAdapter = CountryItemChartsAdapter(ArrayList())
        rv_charts.adapter = chartsAdapter
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getData(false)
        }


        fabFilter.setOnClickListener {
            if(System.currentTimeMillis() - mClicktime < 500)
                return@setOnClickListener
            mClicktime = System.currentTimeMillis();
            val fragment = FilterFragment.newInstance(sortType);
            fragment.setClickListener(this)
            fragment.show(parentFragmentManager,FilterFragment::class.java.name)
        }
    }

    override fun setData(items: ArrayList<CountryItemData>) {
        chartsAdapter?.updateData(items)
    }

    override fun onSortItemClicked(sortType: Constants.SortType) {
        this.sortType = sortType;
        chartsAdapter?.sortItems(sortType)
    }

}
