package com.ddd.domain

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class CheckCurrentUserUseCase @Inject constructor(private val auth: FirebaseAuth) {
    fun currentUser() = auth.currentUser
}