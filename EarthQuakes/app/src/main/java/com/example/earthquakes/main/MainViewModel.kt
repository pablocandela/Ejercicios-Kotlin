package com.example.earthquakes.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.earthquakes.EarthQuake
import com.example.earthquakes.api.ApiResponseStatus
import com.example.earthquakes.database.getDatabase
import kotlinx.coroutines.*
import java.net.UnknownHostException

class MainViewModel(application: Application, sortType: Boolean): AndroidViewModel(application) {

    private val database = getDatabase(application.applicationContext)
    private val repository = MainRepository(database)

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    private var _eqList = MutableLiveData<MutableList<EarthQuake>>()
    val eqList: LiveData<MutableList<EarthQuake>>
        get() = _eqList

    init {
        reloadEarthQuake(sortType)
    }

    fun reloadEarthQuake(sortByMagnitude: Boolean){
        viewModelScope.launch {
            try {
                Log.d("Prueba", sortByMagnitude.toString())
                _status.value = ApiResponseStatus.LOADING
                _eqList.value = repository.fetchEarthQuakes(sortByMagnitude)
                _status.value = ApiResponseStatus.DONE
            } catch (e: UnknownHostException) {
                _status.value = ApiResponseStatus.LOADING
                _eqList.value = repository.getAllEarthQuakesDatabase(sortByMagnitude)
                _status.value = ApiResponseStatus.DONE
            }
        }
    }

}