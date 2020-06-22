package com.ddd.data

import com.ddd.common.TimeProviderManager
import com.ddd.domain.TimeProviderRepository
import javax.inject.Inject

class TimeProviderImpl @Inject constructor() : TimeProviderRepository {

    override fun onTimeSet(hour: Int, min: Int): String = TimeProviderManager.onTimeSet(hour, min)

    override fun dayStringToDateLong(dayString: String): Long =
        TimeProviderManager.dayStringToDataLong(dayString)
}
