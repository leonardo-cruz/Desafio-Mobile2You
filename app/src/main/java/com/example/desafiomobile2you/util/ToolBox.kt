package com.example.desafiomobile2you.util

import java.lang.Exception

fun voteFormated(number : Int) : String{
    try {
        val numberFormated: Float = (number.toFloat() / 1000)
        return String.format("%.1f", numberFormated) + "K Likes"
    } catch (e: Exception){
        return "0"
    }
}

fun yearFormated(date: String): String{
    try {
        var year = date.substring(0, 4)
        return year
    } catch (e: Exception){
        return "0000"
    }
}