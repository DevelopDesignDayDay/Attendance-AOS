package com.ddd.presentation.ui.signup

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.presentation.ui.signup.step.StepNameFragment
import javax.inject.Inject

const val STEP_STATUS = 1
const val STEP_STATUS_PLUS_VALUE = 1

enum class Position(position: String) {
    Designer("Designer"),
    AOS("AOS"),
    iOS("iOS"),
    BackEnd("Backend")
}

class SignUpViewModel @Inject constructor() : ViewModel() {

    sealed class Result {
        data class ReplaceFragment(val fragment: Fragment) : Result()
        data class SelectedPosition(val position: Position) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    private val _liveStep = MutableLiveData<Int>()
    val liveStep: LiveData<Int> = _liveStep


    init {
        _liveResult.value=Result.ReplaceFragment(StepNameFragment.getInstance())
        _liveStep.value = STEP_STATUS
    }

    fun replaceFragment(fragment: Fragment) {
        _liveStep.value = _liveStep.value?.plus(STEP_STATUS_PLUS_VALUE)
        _liveResult.value = Result.ReplaceFragment(fragment)
    }

    fun selectedPosition(position: Position) {
        _liveResult.value = Result.SelectedPosition(position)
    }
}
