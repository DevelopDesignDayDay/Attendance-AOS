package com.ddd.domain

import com.ddd.common.MessageManager.LOGIN_EMPTY_INPUT
import com.ddd.common.MessageManager.LOGIN_VALIDATE_NOT_EMAIL
import com.ddd.common.MessageManager.LOGIN_VALIDATE_NOT_PASSWORD
import java.util.regex.Pattern
import javax.inject.Inject

class CheckSignUpAuthUseCase @Inject constructor() {

    fun checkSignUp(email: String?, pw: String?): Pair<Boolean, String?> {
        return when {
            (email.isNullOrEmpty() || pw.isNullOrEmpty()) -> Pair(false, LOGIN_EMPTY_INPUT)
            validateEmail(email) && validatePassword(pw) -> Pair(true, null)
            !validateEmail(email) -> Pair(false, LOGIN_VALIDATE_NOT_EMAIL)
            !validatePassword(pw) -> Pair(false, LOGIN_VALIDATE_NOT_PASSWORD)
            else -> Pair(false, null)
        }
    }

    private fun validateEmail(email: String): Boolean = validEmailRegex().matcher(email).find()
    private fun validatePassword(pw: String): Boolean = validPasswordRegex().matcher(pw).find()

    private fun validEmailRegex(): Pattern {
        return Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
        )
    }

    private fun validPasswordRegex(): Pattern =
        Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$")
}