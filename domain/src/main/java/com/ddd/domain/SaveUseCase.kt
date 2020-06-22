package com.ddd.domain

import javax.inject.Inject

class SaveUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun saveUser(email: String?, name: String?, type: String?) {
        firebaseRepository.saveUser(email.orEmpty(), name.orEmpty(), type.orEmpty(), false)
    }
}