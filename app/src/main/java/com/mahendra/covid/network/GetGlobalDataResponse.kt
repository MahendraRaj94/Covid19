package com.mahendra.covid.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mahendra.covid.base.Response
import com.mahendra.covid.model.CountryData

class GetGlobalDataResponse : Response() {
    @Expose
    @SerializedName("data")
    var countryData : CountryData? = null


}