package com.ddd.common

import java.text.SimpleDateFormat
import java.util.*

object TimeProviderManager {
    private const val YYYY_MM_DD = "yyyy년 MM월 dd일"
    private const val YYYY_MM_DD_HH_MM = "yyyy년 MM월 dd일 HH시 mm분"
    fun onTimeSet(hour: Int, min: Int): String {
        return SimpleDateFormat(YYYY_MM_DD, Locale.KOREAN).format(Date()) + " ${hour}시 ${min}분"
    }

    fun dayStringToDataLong(dayString: String): Long {
        return SimpleDateFormat(YYYY_MM_DD_HH_MM, Locale.KOREAN).parse(dayString)?.time
            ?: Date().time
    }

    fun dateLongToDayString(date:Long):String{
        return SimpleDateFormat(YYYY_MM_DD_HH_MM, Locale.KOREAN).format(Date(date))
    }
}
