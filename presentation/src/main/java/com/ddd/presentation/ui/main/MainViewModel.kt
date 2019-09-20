package com.ddd.presentation.ui.main

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.GetQRCodeDataUseCase
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainViewModel @Inject constructor(
    getQRCodeDataUseCase: GetQRCodeDataUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    sealed class Result {
        data class InitQRCode(val qrcode: Bitmap) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    init {
        getQRCodeDataUseCase.execute(
            success = {
                Log.e("bit", it.toString())
                _liveResult.value = Result.InitQRCode(it)
            },
            error = {
                Log.e("ee", it.toString())
            }
        )

    }

    fun tempLogout() {
        auth.signOut()
    }

}
