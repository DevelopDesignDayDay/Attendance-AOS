package com.ddd.presentation.ui.splash

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.domain.CheckCurrentUserUseCase
import com.ddd.domain.CheckManagerUseCase
import com.ddd.presentation.ui.main.MainActivity
import com.ddd.presentation.ui.manager.ManagerActivity
import com.ddd.presentation.ui.tutorial.TutorialActivity
import javax.inject.Inject
import kotlin.reflect.KClass

class SplashViewModel @Inject constructor(
    private val checkCurrentUserUseCase: CheckCurrentUserUseCase,
    private val checkManagerUseCase: CheckManagerUseCase
) : ViewModel() {

    sealed class Result {
        data class Error(val msg: String) : Result()
        data class SuccessSignUp<T : Activity>(val nextActivity: KClass<T>) :
            SplashViewModel.Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    fun isLoginUser() {
        (checkCurrentUserUseCase.currentUser())?.let { user ->
            checkManagerUseCase.execute(user.uid,
                success = { isManager ->
                    if (isManager) _liveResult.value =
                        Result.SuccessSignUp(ManagerActivity::class)
                    else _liveResult.value =
                        Result.SuccessSignUp(MainActivity::class)
                },
                error = { e ->
                    _liveResult.value = Result.Error(e.message.orEmpty())
                }
            )
        } ?: run {
            _liveResult.value = Result.SuccessSignUp(TutorialActivity::class)
        }
    }
}