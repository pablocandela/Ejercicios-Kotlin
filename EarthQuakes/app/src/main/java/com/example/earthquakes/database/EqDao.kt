package com.example.earthquakes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.earthquakes.EarthQuake

@Dao
interface EqDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(eqList: MutableList<EarthQuake>)

    @Query("SELECT * FROM TBL_EARTHQUAKES")
    fun getEarthquakes(): MutableList<EarthQuake>

    @Query("SELECT * FROM TBL_EARTHQUAKES ORDER BY time ASC")
    fun sortByTime(): MutableList<EarthQuake>

    @Query("SELECT * FROM TBL_EARTHQUAKES ORDER BY magnitude DESC")
    fun sortByMagnitude(): MutableList<EarthQuake>
}