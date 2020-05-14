package com.mahendra.covid.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mahendra.covid.BR

import com.mahendra.covid.R
import com.mahendra.covid.adapter.ChartsAdapter
import com.mahendra.covid.base.Constants
import com.mahendra.covid.databinding.MainFragmentBinding
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.viewmodel.MainViewModel
import com.mahendra.covid.viewmodel.MainViewNavigator
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(),MainViewNavigator, FilterFragment.SortItemClickListener {
    private var mClicktime : Long = 0;
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var chartsAdapter : ChartsAdapter? = null
    private var sortType : Constants.SortType = Constants.SortType.SET_TO_DEFAULT

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return MainFragmentBinding.inflate(layoutInflater).root
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
            searchView.queryHint = "Enter country name"
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setNavigator(this)
        viewModel.initViews();
        viewModel.getData(false)?.observe(viewLifecycleOwner,object : Observer<ArrayList<CountryData>>{
            override fun onChanged(t: ArrayList<CountryData>?) {
                if(t != null){
                    chartsAdapter?.updateData(t);
                }
            }
        })
        chartsAdapter?.sortItems(sortType)
    }

    override fun initViews() {
        rv_charts.layoutManager = LinearLayoutManager(context)
        chartsAdapter = ChartsAdapter(ArrayList())
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

    override fun setData(items: ArrayList<CountryData>) {
        chartsAdapter?.updateData(items)
    }

    override fun onSortItemClicked(sortType: Constants.SortType) {
        this.sortType = sortType;
        chartsAdapter?.sortItems(sortType)
    }

}
