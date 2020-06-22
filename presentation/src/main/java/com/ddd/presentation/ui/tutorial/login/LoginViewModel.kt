package com.ddd.presentation.ui.tutorial.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.common.MessageManager.LOGIN_EMPTY_INPUT
import com.ddd.common.MessageManager.LOGIN_INPUT_ERROR_MSG
import com.ddd.domain.CheckManagerUseCase
import com.ddd.domain.SaveUseCase
import com.ddd.presentation.ui.main.MainActivity
import com.ddd.presentation.ui.manager.ManagerActivity
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.reflect.KClass

class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val saveUserUseCase: SaveUseCase,
    private val checkManagerUseCase: CheckManagerUseCase
) : ViewModel() {
    sealed class Result {
        data class Error(val msg: String) : Result()
        data class SuccessSignUp<T : Activity>(val nextActivity: KClass<T>) : Result()
        data class ShowLoginBottomSheet(val loginBottomSheet: LoginBottomSheetDialog) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult
    private val _liveEmailInput = MutableLiveData<String>()
    val liveEmailInput: LiveData<String> = _liveEmailInput
    private val _livePasswordInput = MutableLiveData<String>()
    val livePasswordInput: LiveData<String> = _livePasswordInput
    private val _liveProgress = MutableLiveData<Boolean>()
    val liveProgress: LiveData<Boolean> = _liveProgress

    fun emailInputChanged(input: CharSequence) {
        _liveEmailInput.value = input.toString()
    }

    fun passwordInputChanged(input: CharSequence) {
        _livePasswordInput.value = input.toString()
    }

    fun showLoginBottomSheet() {
        _liveResult.value = Result.ShowLoginBottomSheet(LoginBottomSheetDialog.getInstance(this))
    }

    fun signIn() {
        val (email, pw) = Pair(_liveEmailInput.value.orEmpty(), _livePasswordInput.value.orEmpty())
        if (email.isEmpty() || pw.isEmpty()) {
            _liveResult.value = Result.Error(LOGIN_EMPTY_INPUT)
            return
        }
        _liveProgress.value = true
        auth.signInWithEmailAndPassword(email, pw)
            .addOnCompleteListener {
                _liveProgress.value = false
                if (it.isSuccessful) {
                    checkManagerUseCase.execute(it.result?.user?.uid,
                        success = { isManager ->
                            if (isManager) _liveResult.value =
                                Result.SuccessSignUp(ManagerActivity::class)
                            else _liveResult.value = Result.SuccessSignUp(MainActivity::class)
                        },
                        error = { e -> _liveResult.value = Result.Error(e.message.orEmpty()) }
                    )
                } else {
                    _liveResult.value = Result.Error(LOGIN_INPUT_ERROR_MSG)
                }
            }
    }
}