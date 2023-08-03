package com.example.composeexample.domain.model

import com.google.gson.Gson
import java.io.Serializable

data class Hero(val name:String,val photo:String,val description:String,val favorite:Boolean,val id:String): Serializable{
    companion object{
        fun fromJson(json:String):Array<Hero>{
            return Gson().fromJson(json,Array<Hero>::class.java)
        }

        fun dummy():Hero{
            return Hero("Sample","https://eitrawmaterials.eu/wp-content/uploads/2016/09/person-icon.png","Description",false,"")
        }

    }
}