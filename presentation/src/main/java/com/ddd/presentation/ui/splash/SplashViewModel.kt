package com.ddd.presentation.ui.splash

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.CheckCurrentUserUseCase
import com.ddd.presentation.ui.main.MainActivity
import com.ddd.presentation.ui.tutorial.TutorialActivity
import javax.inject.Inject
import kotlin.reflect.KClass

class SplashViewModel @Inject constructor(
    private val checkCurrentUserUseCase: CheckCurrentUserUseCase
) : ViewModel() {

    sealed class Result {
        data class SuccessSignUp<T : Activity>(val nextActivity: KClass<T>) :
            SplashViewModel.Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    fun isLoginUser() {
        val nextActivity = if (checkCurrentUserUseCase.currentUser() != null) {
            MainActivity::class
        } else {
            TutorialActivity::class
        }
        _liveResult.value = Result.SuccessSignUp(nextActivity)
    }
}