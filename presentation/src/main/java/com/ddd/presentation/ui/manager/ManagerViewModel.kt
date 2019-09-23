package com.ddd.presentation.ui.manager

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.FirebaseRepository
import javax.inject.Inject

class ManagerViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _livePlace = MutableLiveData<String>()
    val livePlace = _livePlace

    fun addAttendance(uuid: String) {
        firebaseRepository.saveAttendance(uuid,
            _livePlace.value.orEmpty(),
            System.currentTimeMillis().toString(),
            (System.currentTimeMillis() + 10000).toString(),
            result = {
                Log.e("!", it.toString())
            },
            error = {

            })
    }

    fun placeInputChanged(input:CharSequence){
        _livePlace.value=input.toString()
    }

}
