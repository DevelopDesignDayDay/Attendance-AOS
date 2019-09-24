package com.ddd.domain

interface TimeProviderRepository {
    fun onTimeSet(hour: Int, min: Int): String
    fun dayStringToDateLong(dayString:String):Long
}
