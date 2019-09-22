package com.ddd.presentation.ui.main

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.GetQRCodeDataUseCase
import com.ddd.presentation.ui.tutorial.TutorialActivity
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainViewModel @Inject constructor(
    getQRCodeDataUseCase: GetQRCodeDataUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    sealed class Result {
        data class InitQRCode(val qrcode: Bitmap) : Result()
        data class LoginActivity<T>(val nextActivity: Class<T>) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    init {
        getQRCodeDataUseCase.execute(
            success = {
                _liveResult.value = Result.InitQRCode(it)
            },
            error = {
                _liveResult.value = Result.LoginActivity(TutorialActivity::class.java)
            }
        )

    }

    fun tempLogout() {
        auth.signOut()
    }

}
