package com.example.earthquakes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_earthquakes")
data class EarthQuake(@PrimaryKey val id: String, val place: String, val magnitude: String, val time: Long, val longitude: Double, val latitude: Double) {
}