package com.ddd.presentation.ui.tutorial

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddd.presentation.R
import com.ddd.presentation.ui.signup.SignUpActivity
import javax.inject.Inject
import kotlin.reflect.KClass

class TutorialViewModel @Inject constructor() : ViewModel() {
    companion object {
        const val FIRST_TUTORIAL_TITLE = "간편한 출석체크"
        const val FIRST_TUTORIAL_SUB_TITLE = "QR 코드를 통한 본인 인증으로\n 5초면 출석체크 완료!"
        const val SECOND_TUTORIAL_TITLE = "스터디 일정체크"
        const val SECOND_TUTORIAL_SUB_TITLE = "세션 날짜와 장소, 시간대까지\n 이제 앱에서 바로 체크하세요 :)"
    }
    sealed class Result {
        data class MoveSignUp<T : Activity>(val activity: KClass<T>) : Result()
        data class TutorialItems(val items: List<Triple<Int, String, String>>) : Result()
    }

    private val _liveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = _liveResult

    fun moveSignUp() {
        _liveResult.value = Result.MoveSignUp(SignUpActivity::class)
    }

    private val items = listOf(
        Triple(
            R.drawable.onboarding_1_character,
            FIRST_TUTORIAL_TITLE,
            FIRST_TUTORIAL_SUB_TITLE
        ),
        Triple(
            R.drawable.onboarding_2_character,
            SECOND_TUTORIAL_TITLE,
            SECOND_TUTORIAL_SUB_TITLE
        )
    )

    fun getTutorialItems() {
        _liveResult.value = Result.TutorialItems(items)
    }
}