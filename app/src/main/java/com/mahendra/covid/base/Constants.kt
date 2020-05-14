package com.mahendra.covid.base

class Constants {
    companion object{
        const val KEY_COUNTRY_DATA: String = "countryData"
//        const val BASE_URL = "http://172.20.10.3:8080";
        const val BASE_URL = "http://192.168.0.110:8080";
    }

    class CasesTitle{
        companion object{
            const val CONFIRMED : String = "Confirmed"
            const val DECEASED : String = "Deceased";
            const val SERIOUS : String = "Serious"
            const val RECOVERED : String = "Recovered"
        }

    }
    class ChartTitle{
        companion object{
            const val LINE_CHART : String = "Line chart"
            const val BAR_CHART : String = "Bar chart";
            const val PIE_CHART : String = "Pie chart"
            const val HORIZONTAL_CHART : String = "Horizontal bar chart"
            const val RADAR_CHART : String = "Radar chart"
        }

    }
    class ChartType{
        companion object{
            const val VALUE_LINE_CHART : Int = 0
            const val VALUE_BAR_CHART : Int = 1;
            const val VALUE_PIE_CHART : Int = 2;
            const val VALUE_HORIZONTAL_CHART : Int = 3
            const val PERCENTAGE_LINE_CHART : Int = 4
            const val PERCENTAGE_BAR_CHART : Int = 5
            const val PERCENTAGE_PIE_CHART : Int = 6
            const val PERCENTAGE_HORIZONTAL_CHART : Int = 7
        }

    }

    enum class SortType{
        SET_TO_DEFAULT,
        ACTIVE_UP,
        ACTIVE_DOWN,
        DEATH_UP,
        DEATH_DOWN,
        RECOVERED_UP,
        RECOVERED_DOWN,
        SERIOUS_UP,
        SERIOUS_DOWN
    }


}