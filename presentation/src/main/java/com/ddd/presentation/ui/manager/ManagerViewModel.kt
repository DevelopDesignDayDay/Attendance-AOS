package com.ddd.presentation.ui.manager

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.AddAttendanceUseCase
import com.ddd.domain.FirebaseRepository
import com.ddd.domain.TimeProviderRepository
import javax.inject.Inject

class ManagerViewModel @Inject constructor(
    private val addAttendanceUseCase: AddAttendanceUseCase,
    private val timeProviderRepository: TimeProviderRepository
) : ViewModel() {

    private val _livePlace = MutableLiveData<String>()
    val livePlace: LiveData<String> = _livePlace
    private val _liveDate = MutableLiveData<String>()
    val liveDate: LiveData<String> = _liveDate

    fun addAttendance(uuid: String) {
        addAttendanceUseCase.execute(AddAttendanceUseCase.Params(
            uuid,
            _livePlace.value.orEmpty(),
            timeProviderRepository.dayStringToDateLong(_liveDate.value.orEmpty()).toString(),
            System.currentTimeMillis().toString()),
            success = {
                Log.e("success", "success")
            },
            error = {}
        )
    }

    fun placeInputChanged(input: CharSequence) {
        _livePlace.value = input.toString()
    }

    fun onTimeSet(hour: Int, min: Int) {
        _liveDate.value = timeProviderRepository.onTimeSet(hour, min)
    }

}
