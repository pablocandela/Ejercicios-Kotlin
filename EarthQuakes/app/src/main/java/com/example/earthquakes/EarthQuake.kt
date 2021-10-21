package com.example.earthquakes

data class EarthQuake(val id: String, val place: String, val magnitude: String, val time: Long, val longitude: Double, val latitude: Double) {
}