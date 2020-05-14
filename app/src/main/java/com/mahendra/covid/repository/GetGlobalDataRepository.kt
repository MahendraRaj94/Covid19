package com.mahendra.covid.repository

import androidx.lifecycle.MutableLiveData
import com.mahendra.covid.base.RestApi
import com.mahendra.covid.base.RetrofitService
import com.mahendra.covid.model.CountryData
import com.mahendra.covid.network.GetCountrywiseDataResponse
import com.mahendra.covid.network.GetGlobalDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetGlobalDataRepository {
    private lateinit var restApi : RestApi
    private constructor(){
        restApi = RetrofitService.cteateService(RestApi::class.java)
    }

    companion object{
        private var globalDataRepository : GetGlobalDataRepository? = null;
        fun getInstance() : GetGlobalDataRepository{
            if(globalDataRepository == null){
                globalDataRepository = GetGlobalDataRepository();
            }

            return globalDataRepository!!;
        }
    }


    fun getGlobalData() : MutableLiveData<CountryData>{
        var data : MutableLiveData<CountryData> = MutableLiveData<CountryData>();

        restApi.getTotalData().enqueue(object : Callback<GetGlobalDataResponse>{
            override fun onFailure(call: Call<GetGlobalDataResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<GetGlobalDataResponse>,
                response: Response<GetGlobalDataResponse>
            ) {
                if(response == null || response.body() == null){
                    data.value = null
                }else{
                    data.value = response.body()!!.countryData
                }
            }
        })

        return data;
    }
}