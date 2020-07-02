package com.ddd.domain

import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val repository: FirebaseRepository) {

    operator fun invoke() = repository.signOut()
}