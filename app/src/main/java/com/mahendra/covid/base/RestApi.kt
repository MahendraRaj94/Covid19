package com.mahendra.covid.base

import com.mahendra.covid.network.GetCountrywiseDataResponse
import com.mahendra.covid.network.GetGlobalDataResponse
import com.mahendra.covid.network.GetIndiaStateDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {

    @GET("/india/stateData")
    fun getStatewiseData() : Call<GetIndiaStateDataResponse>

    @GET("/countryData")
    fun getCountryWiseCovidData() : Call<GetCountrywiseDataResponse>

    @GET("/total")
    fun getTotalData() : Call<GetGlobalDataResponse>

    @GET("/india/total")
    fun getIndiaTotalData() : Call<GetGlobalDataResponse>
}