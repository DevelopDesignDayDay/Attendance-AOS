package com.ddd.presentation.ui.signup

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.common.MessageManager.LOGIN_SIGN_UP_FAIL_ERROR_MSG
import com.ddd.common.default
import com.ddd.domain.CheckSignUpAuthUseCase
import com.ddd.domain.SaveUseCase
import com.ddd.presentation.ui.signup.step.StepFinalFragment
import com.ddd.presentation.ui.signup.step.StepNameFragment
import com.ddd.presentation.ui.signup.step.StepPositionFragment
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


const val STEP_STATUS = 1
const val STEP_STATUS_PLUS_VALUE = 1

enum class Position(position: String) {
    Designer("Designer"),
    AOS("AOS"),
    iOS("iOS"),
    BackEnd("Backend")
}

class SignUpViewModel @Inject constructor(
    private val checkSignUpAuthUseCase: CheckSignUpAuthUseCase,
    private val saveUserUseCase: SaveUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    sealed class Result {
        data class ReplaceFragment(val fragment: Fragment) : Result()
        data class SelectedPosition(val position: Position) : Result()
        data class ToastMessage(val msg: String) : Result()
        object Finish : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    private val _liveStep = MutableLiveData<Int>()
    val liveStep: LiveData<Int> = _liveStep

    private val _liveFirstName = MutableLiveData<String>()
    val liveFirstName: LiveData<String> = _liveFirstName

    private val _liveLastName = MutableLiveData<String>()
    val liveLastName: LiveData<String> = _liveLastName

    private val _liveEmail = MutableLiveData<String>()
    val liveEmail: LiveData<String> = _liveEmail

    private val _livePW = MutableLiveData<String>()
    val livePW: LiveData<String> = _livePW

    private val _liveEnableNextBtn = MutableLiveData<Boolean>()
    val liveEnableNextBtn: LiveData<Boolean> = _liveEnableNextBtn

    private val _liveLoading = MutableLiveData<Boolean>()
    val liveLoading: LiveData<Boolean> = _liveLoading.default(false)

    init {
        _liveResult.value=Result.ReplaceFragment(StepNameFragment.getInstance())
        _liveStep.value = STEP_STATUS
    }

    private fun selectedStepPositionFragment(stepPositionFragment: StepPositionFragment) {
        val (result, msg) = checkSignUpAuthUseCase.checkSignUp(_liveEmail.value, _livePW.value)
        if (result) refresh(stepPositionFragment)
        else _liveResult.value = Result.ToastMessage(msg.orEmpty())
    }

    private fun selectedStepFinalFragment(stepFinalFragment: StepFinalFragment) {
        _liveLoading.value = true
        auth.createUserWithEmailAndPassword(
            _liveEmail.value.orEmpty(),
            _livePW.value.orEmpty()
        ).addOnCompleteListener {
            _liveLoading.value = false
            if (it.isSuccessful) {
                saveUserUseCase.saveUser(
                    _liveEmail.value,
                    _liveFirstName.value + _liveLastName.value,
                    (_liveResult.value as Result.SelectedPosition).position.name
                )
                _liveResult.value = Result.ReplaceFragment(stepFinalFragment)
            } else {
                _liveResult.value = Result.ToastMessage(LOGIN_SIGN_UP_FAIL_ERROR_MSG)
            }
        }.addOnFailureListener {
            _liveResult.value = Result.ToastMessage(LOGIN_SIGN_UP_FAIL_ERROR_MSG)
        }
    }

    fun finish() {
        _liveResult.value = Result.Finish
    }
    fun replaceFragment(fragment: Fragment) {
        when (fragment) {
            is StepPositionFragment -> selectedStepPositionFragment(fragment)
            is StepFinalFragment -> selectedStepFinalFragment(fragment)
            else -> refresh(fragment)
        }
    }

    private fun refresh(fragment: Fragment) {
        _liveEnableNextBtn.value = false
        _liveStep.value = _liveStep.value?.plus(STEP_STATUS_PLUS_VALUE)
        _liveResult.value = Result.ReplaceFragment(fragment)
    }

    fun selectedPosition(position: Position) {
        _liveResult.value = Result.SelectedPosition(position)
    }

    fun firstNameChanged(input: CharSequence) {
        _liveFirstName.value = input.toString()
        checkSignUpAuthUseCase.checkSignUp(_liveFirstName.value, _liveLastName.value)
    }

    fun lastNameChanged(input: CharSequence) {
        _liveLastName.value = input.toString()
    }

    fun emailInputChanged(input: CharSequence) {
        _liveEmail.value = input.toString()
    }

    fun pwInputChanged(input: CharSequence) {
        _livePW.value = input.toString()
        checkSignUpAuthUseCase.checkSignUp(_liveEmail.value, _livePW.value)
    }
}
