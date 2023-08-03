package com.example.composeexample.components

class RangeSelectorDelegate(
    private val minValue : Int,
    private val maxValue : Int
) {

    var currentBotton = minValue
    var currentTop = maxValue

    fun moveTop(newValue : Int) {
        currentTop = if(newValue<currentBotton){
            currentBotton + 1
        }else if(newValue>currentTop){
            currentTop
        }else{
            newValue
        }
    }

    fun moveBottom(newValue: Int){
        currentBotton = if(newValue>currentTop){
            currentTop - 1
        }else if(newValue<currentBotton){
            currentBotton
        }else{
            newValue
        }
    }
}