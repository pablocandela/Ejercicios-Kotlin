package com.hackaprende.basketballscore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var localScore: MutableLiveData<Int> =  MutableLiveData()
    var visitorScore: MutableLiveData<Int> = MutableLiveData()

    init {
        resetScores()
    }

    fun resetScores() {
        localScore.value = 0
        visitorScore.value = 0
    }

    fun addPointsToScore(points: Int, isLocal: Boolean) {
        if(isLocal)
            localScore.value = localScore.value!! + points
        else
            visitorScore.value = visitorScore.value!! + points

    }

    fun decreaseScore(isLocal: Boolean){
        if (localScore.value!! > 0 && isLocal)
            localScore.value = localScore.value!! - 1
        else if(localScore.value!! > 0)
            visitorScore.value = visitorScore.value!! - 1
    }
}