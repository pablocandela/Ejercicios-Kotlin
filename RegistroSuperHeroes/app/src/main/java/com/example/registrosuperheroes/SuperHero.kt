package com.example.registrosuperheroes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SuperHero(val name:String, val nickname: String, val bio: String, val power: Float) :
    Parcelable {
}