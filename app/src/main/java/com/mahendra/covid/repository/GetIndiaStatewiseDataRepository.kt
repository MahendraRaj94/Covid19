package com.mahendra.covid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahendra.covid.base.RestApi
import com.mahendra.covid.base.RetrofitService
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.model.CountryItemData
import com.mahendra.covid.network.GetCountrywiseDataResponse
import com.mahendra.covid.network.GetIndiaStateDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetIndiaStatewiseDataRepository {
    private lateinit var restApi : RestApi
    private constructor(){
        restApi = RetrofitService.cteateService(RestApi::class.java)
    }

    companion object{
        private var countrywiseDataRepository : GetIndiaStatewiseDataRepository? = null;
        fun getInstance() : GetIndiaStatewiseDataRepository{
            if(countrywiseDataRepository == null){
                countrywiseDataRepository = GetIndiaStatewiseDataRepository();
            }

            return countrywiseDataRepository!!;
        }
    }


    fun getIndiaStatewiseData() : MutableLiveData<ArrayList<CountryItemData>>{
        var data : MutableLiveData<ArrayList<CountryItemData>> = MutableLiveData<ArrayList<CountryItemData>>();

        restApi.getStatewiseData().enqueue(object : Callback<GetIndiaStateDataResponse>{
            override fun onFailure(call: Call<GetIndiaStateDataResponse>, t: Throwable) {
                data.value = ArrayList<CountryItemData>() }

            override fun onResponse(
                call: Call<GetIndiaStateDataResponse>,
                response: Response<GetIndiaStateDataResponse>
            ) {
                if(response == null || response.body() == null){
                    data.value = ArrayList<CountryItemData>()
                }else{
                    data.value = response.body()!!.countryDataItems
                }
            }
        })

        return data;
    }
}