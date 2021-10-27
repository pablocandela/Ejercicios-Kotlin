package com.example.earthquakes.main

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.earthquakes.EarthQuake
import com.example.earthquakes.api.EqJsonResponse
import com.example.earthquakes.api.Features
import com.example.earthquakes.api.service
import com.example.earthquakes.database.EqDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val database: EqDatabase) {


     suspend fun fetchEarthQuakes(sortByMagnitude: Boolean): MutableList<EarthQuake> {
        return withContext(Dispatchers.IO) {
            val eqJsonResponse = service.getLasHourEarthQuakes()
            val eqList = parseEqResult(eqJsonResponse)
            database.eqDao.insertAll(eqList)
            if(sortByMagnitude){
                database.eqDao.sortByMagnitude()
            }else{
                database.eqDao.sortByTime()
            }
        }
    }

    suspend  fun getAllEarthQuakesDatabase(sortByMagnitude: Boolean): MutableList<EarthQuake>{
        return withContext(Dispatchers.IO) {
            if(sortByMagnitude){
                database.eqDao.sortByMagnitude()
            }else{
                database.eqDao.sortByTime()
            }
        }
    }

    private fun parseEqResult(eqJsonResponse: EqJsonResponse): MutableList<EarthQuake>{
        val eqList = mutableListOf<EarthQuake>()
        val features = eqJsonResponse.features
        for(feature: Features in features) {
            val id = feature.id
            val properties = feature.properties
            val geometry = feature.geometry

            var magnitude = properties.mag
            val place = properties.place
            val time = properties.time
            val latitude = geometry.latitude
            val longitude = geometry.longitude
            val magnitudeString: String = magnitude.format(2)
            val earthquake = EarthQuake(id,place,magnitudeString, time, longitude, latitude)
            eqList.add(earthquake)
        }
        return eqList
    }

    private fun Double.format(digits: Int): String {
        return "%.${digits}f".format(this).apply {
            plus("0")
        }
    }
}