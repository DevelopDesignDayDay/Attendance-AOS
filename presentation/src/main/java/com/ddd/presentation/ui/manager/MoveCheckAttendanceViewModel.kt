package com.ddd.presentation.ui.manager

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.SearchNameUseCase
import com.ddd.domain.entity.DomainEntity
import javax.inject.Inject

class MoveCheckAttendanceViewModel @Inject constructor(
    private val searchNameUseCase: SearchNameUseCase
) : ViewModel() {

    sealed class Result {
        object EmptyName : Result()
        data class Data(val items: List<DomainEntity.UserEntity>) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    fun searchName(name: String?) {
        name?.let {
            searchNameUseCase.invoke(it) { entity ->
                Log.e("sdf",entity.count().toString())
                _liveResult.value = Result.Data(entity)
            }
        } ?: run { _liveResult.value = Result.EmptyName }
    }
}