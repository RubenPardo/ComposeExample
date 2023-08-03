package com.example.composeexample.domain.model

import com.google.gson.Gson
import java.io.Serializable

data class HeroDO(val name:String, val photo:String, val description:String, val favorite:Boolean, val id:String): Serializable{
    companion object{
        fun fromJson(json:String):Array<HeroDO>{
            return Gson().fromJson(json,Array<HeroDO>::class.java)
        }
    }
}