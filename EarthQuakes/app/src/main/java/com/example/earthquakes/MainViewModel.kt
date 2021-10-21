package com.example.earthquakes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.Dispatcher
import org.json.JSONObject
import kotlin.math.log

class MainViewModel: ViewModel() {
    private var _eqList = MutableLiveData<MutableList<EarthQuake>>()
    val eqList: LiveData<MutableList<EarthQuake>>
        get() = _eqList

    private val repository = MainRepository()
    init {
       viewModelScope.launch {
           _eqList.value = repository.fetchEarthQuakes()
        }
    }


}