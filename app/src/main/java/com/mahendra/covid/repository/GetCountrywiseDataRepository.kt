package com.mahendra.covid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahendra.covid.base.RestApi
import com.mahendra.covid.base.RetrofitService
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.network.GetCountrywiseDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCountrywiseDataRepository {
    private lateinit var restApi : RestApi
    private constructor(){
        restApi = RetrofitService.cteateService(RestApi::class.java)
    }

    companion object{
        private var countrywiseDataRepository : GetCountrywiseDataRepository? = null;
        fun getInstance() : GetCountrywiseDataRepository{
            if(countrywiseDataRepository == null){
                countrywiseDataRepository = GetCountrywiseDataRepository();
            }

            return countrywiseDataRepository!!;
        }
    }


    fun getCountrywiseData() : MutableLiveData<ArrayList<CountryData>>{
        var data : MutableLiveData<ArrayList<CountryData>> = MutableLiveData<ArrayList<CountryData>>();

        restApi.getCountryWiseCovidData().enqueue(object : Callback<GetCountrywiseDataResponse>{
            override fun onFailure(call: Call<GetCountrywiseDataResponse>, t: Throwable) {
                data.value = ArrayList<CountryData>()
            }

            override fun onResponse(
                call: Call<GetCountrywiseDataResponse>,
                response: Response<GetCountrywiseDataResponse>
            ) {
                if(response == null || response.body() == null){
                    data.value = ArrayList<CountryData>()
                }else{
                    data.value = response.body()!!.countryDataItems
                }
            }
        })

        return data;
    }
}