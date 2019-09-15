package com.ddd.presentation.ui.tutorial

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.presentation.ui.signup.SignUpActivity
import javax.inject.Inject
import kotlin.reflect.KClass

class TutorialViewModel @Inject constructor() : ViewModel() {

    sealed class Result {
        data class MoveSignUp<T : Activity>(val activity: KClass<T>) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    fun moveSignUp() {
        _liveResult.value = Result.MoveSignUp(SignUpActivity::class)
    }
}