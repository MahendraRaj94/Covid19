package com.mahendra.covid.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.mahendra.covid.R
import com.mahendra.covid.base.Constants
import com.mahendra.covid.databinding.FilterFragmentBinding
import com.mahendra.covid.viewmodel.FilterViewModel
import kotlinx.android.synthetic.main.filter_fragment.*

class FilterFragment : BottomSheetDialogFragment(), View.OnClickListener {

    var sortItemClickListener : SortItemClickListener? = null
    var sortType : Constants.SortType? = null;
    companion object {
        const val KEY_SORT_TYPE = "sortType"
        fun newInstance(sortType: Constants.SortType): FilterFragment{
            val fragment  = FilterFragment();
            fragment.arguments = Bundle().apply {
                putSerializable(KEY_SORT_TYPE,sortType)
            }
            return fragment;
        }
    }

    private lateinit var viewModel: FilterViewModel

    fun setClickListener(listener: SortItemClickListener){
        this.sortItemClickListener = listener;
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FilterFragmentBinding.inflate(layoutInflater).root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)
        sortType = arguments?.getSerializable(KEY_SORT_TYPE) as Constants.SortType?;
        initViews();

    }

    private fun initViews() {
        setOnClickListeners()
        setSortTypeUI(sortType)
    }

    private fun setSortTypeUI(sortType: Constants.SortType?) {
        context?.let {c ->
            sortType?.let {
                when(it){
                    Constants.SortType.SET_TO_DEFAULT ->{
                        tv_set_to_default.setTextColor(c.getColor(R.color.colorAccent))
                    }
                    Constants.SortType.SERIOUS_UP ->{
                        iv_serious_up.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                    Constants.SortType.SERIOUS_DOWN ->{
                        iv_serious_down.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                    Constants.SortType.RECOVERED_UP ->{
                        iv_recovered_up.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                    Constants.SortType.RECOVERED_DOWN ->{
                        iv_recovered_down.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                    Constants.SortType.DEATH_DOWN ->{
                        iv_death_down.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                    Constants.SortType.DEATH_UP ->{
                        iv_death_up.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                    Constants.SortType.ACTIVE_DOWN ->{
                        iv_active_down.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                    Constants.SortType.ACTIVE_UP ->{
                        iv_active_up.setColorFilter(c.resources.getColor(R.color.colorAccent));
                    }
                }
            }

        }
    }

    private fun setOnClickListeners() {
        iv_active_up.setOnClickListener(this)
        iv_active_down.setOnClickListener(this)
        iv_serious_down.setOnClickListener(this)
        iv_serious_up.setOnClickListener(this)
        iv_recovered_down.setOnClickListener(this)
        iv_recovered_up.setOnClickListener(this)
        iv_serious_up.setOnClickListener(this)
        iv_serious_down.setOnClickListener(this)
        iv_death_down.setOnClickListener(this)
        iv_death_up.setOnClickListener(this)
        tv_set_to_default.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_set_to_default ->{
                sortType = Constants.SortType.SET_TO_DEFAULT;
            }
            R.id.iv_active_up ->{
                sortType = Constants.SortType.ACTIVE_UP;
            }
            R.id.iv_active_down ->{
                sortType = Constants.SortType.ACTIVE_DOWN;
            }
            R.id.iv_death_up ->{
                sortType = Constants.SortType.DEATH_UP;
            }
            R.id.iv_death_down ->{
                sortType = Constants.SortType.DEATH_DOWN;
            }
            R.id.iv_recovered_down ->{
                sortType = Constants.SortType.RECOVERED_DOWN;
            }
            R.id.iv_recovered_up ->{
                sortType = Constants.SortType.RECOVERED_UP;
            }
            R.id.iv_serious_down ->{
                sortType = Constants.SortType.SERIOUS_DOWN;
            }
            R.id.iv_serious_up ->{
                sortType = Constants.SortType.SERIOUS_UP;
            }
        }
        sortItemClickListener?.onSortItemClicked(sortType!!)
        dismiss()
    }

    interface SortItemClickListener{
        fun onSortItemClicked(sortType: Constants.SortType)
    }

}
