package com.example.earthquakes

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import java.text.DecimalFormat

class MainRepository {

    public suspend fun fetchEarthQuakes(): MutableList<EarthQuake> {
        return withContext(Dispatchers.IO) {
            val eqJsonResponse = service.getLasHourEarthQuakes()
            val eqList = parseEqResult(eqJsonResponse)
            eqList
        }


    }

    private fun parseEqResult(eqJsonResponse: EqJsonResponse): MutableList<EarthQuake>{
        val eqList = mutableListOf<EarthQuake>()
        val features = eqJsonResponse.features
        for(feature:Features in features) {
            val id = feature.id
            val properties = feature.properties
            val geometry = feature.geometry

            var magnitude = properties.mag
            val place = properties.place
            val time = properties.time
            val latitude = geometry.latitude
            val longitude = geometry.longitude
            val magnitudeString = magnitude.format(2)
            val earthquake = EarthQuake(id,place,magnitudeString, time, longitude, latitude)
            eqList.add(earthquake)
        }
        return eqList
    }

    fun Double.format(digits: Int): String{
        val newDouble = "%.${digits}f".format(this)
        newDouble.plus("0")
        return newDouble
    }
}