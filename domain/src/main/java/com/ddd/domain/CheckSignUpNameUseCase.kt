package com.ddd.domain

import javax.inject.Inject

class CheckSignUpNameUseCase @Inject constructor() {

    fun checkNameStepInput(firstName: String?, lastName: String?) =
        !firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()
}