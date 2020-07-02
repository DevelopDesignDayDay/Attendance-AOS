package com.ddd.presentation.ui.manager

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.AddAttendanceUseCase
import com.ddd.domain.LogoutUseCase
import com.ddd.domain.TimeProviderRepository
import javax.inject.Inject

class ManagerViewModel @Inject constructor(
    private val addAttendanceUseCase: AddAttendanceUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val timeProviderRepository: TimeProviderRepository
) : ViewModel() {

    sealed class Navigation(){
        object MoveCheckAttendance : Navigation()
        object MoveLoginActivity : Navigation()
    }

    private val _liveDate = MutableLiveData<String>()
    val liveDate: LiveData<String> = _liveDate

    private val _liveNavigation = MutableLiveData<Navigation>()
    val liveNavigation: LiveData<Navigation> = _liveNavigation

    fun addAttendance(uuid: String) {
        addAttendanceUseCase.execute(AddAttendanceUseCase.Params(
            uuid,
            timeProviderRepository.dayStringToDateLong(_liveDate.value.orEmpty()).toString(),
            System.currentTimeMillis().toString()
        ), success = {
            Log.e("success", "success")
        }, error = {

        }
        )
    }

    fun onTimeSet(hour: Int, min: Int) {
        _liveDate.value = timeProviderRepository.onTimeSet(hour, min)
    }

    fun logout(){
        logoutUseCase.invoke()
        _liveNavigation.value = Navigation.MoveLoginActivity
    }

    fun moveCheckAttendance(){
        _liveNavigation.value = Navigation.MoveCheckAttendance
    }
}
