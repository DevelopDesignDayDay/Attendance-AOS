package com.ddd.presentation.ui.signup

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.presentation.ui.signup.step.StepNameFragment
import javax.inject.Inject

class SignUpViewModel @Inject constructor() : ViewModel() {

    sealed class Result {
        data class ReplaceFragment(val fragment: Fragment) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    init {
        _liveResult.value=Result.ReplaceFragment(StepNameFragment.getInstance())
    }

    fun replaceFragment(fragment: Fragment) {
        _liveResult.value = Result.ReplaceFragment(fragment)
    }
}
