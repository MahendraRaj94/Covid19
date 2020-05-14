package com.mahendra.covid.model

import android.os.Parcel
import android.os.Parcelable
import com.mahendra.covid.base.Constants

class CountryItemData() : Parcelable {
    var name: String? = null
    var parentName: String? = null
    var confirmedCases: Long = 0
    var changesToday: Long = 0
    var percentageDayChange: String? = null
    var deceased: Long = 0
    var deceasedChangesToday: Long = 0
    var deceasedPercentage: String? = null
    var recovered: Long = 0
    var serious: Long = 0
    var activeCases: Long = 0
    var chartType : Int = Constants.ChartType.VALUE_BAR_CHART


    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        parentName = parcel.readString()
        confirmedCases = parcel.readLong()
        changesToday = parcel.readLong()
        percentageDayChange = parcel.readString()
        deceased = parcel.readLong()
        deceasedChangesToday = parcel.readLong()
        deceasedPercentage = parcel.readString()
        recovered = parcel.readLong()
        serious = parcel.readLong()
        activeCases = parcel.readLong()
        chartType = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(parentName)
        parcel.writeLong(confirmedCases)
        parcel.writeLong(changesToday)
        parcel.writeString(percentageDayChange)
        parcel.writeLong(deceased)
        parcel.writeLong(deceasedChangesToday)
        parcel.writeString(deceasedPercentage)
        parcel.writeLong(recovered)
        parcel.writeLong(serious)
        parcel.writeLong(activeCases)
        parcel.writeInt(chartType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryItemData> {
        override fun createFromParcel(parcel: Parcel): CountryItemData {
            return CountryItemData(parcel)
        }

        override fun newArray(size: Int): Array<CountryItemData?> {
            return arrayOfNulls(size)
        }
    }


}