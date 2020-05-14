package com.mahendra.covid.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Response {
    @Expose
    @SerializedName("status")
    var status : String? = null
    
    @Expose
    @SerializedName("statusCode")
    var statusCode : Int = -1;
}