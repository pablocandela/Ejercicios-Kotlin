package com.example.earthquakes

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

interface EqApiService {
    @GET("all_day.geojson")
    suspend fun getLasHourEarthQuakes(): EqJsonResponse
}

private var retrofit = Retrofit.Builder()
    .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
    .addConverterFactory(MoshiConverterFactory.create()) //ScalarsConverterFactory para obtener un string
    .build()

var service: EqApiService = retrofit.create(EqApiService::class.java)