package com.ddd.domain

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val auth: FirebaseAuth) {

    suspend fun login(email: String, pw: String) = withContext(Dispatchers.IO) {
        if (email.isEmpty() || pw.isEmpty()) cancel()
        val liveData = MutableLiveData<Boolean>()
        auth.signInWithEmailAndPassword(email, pw)
            .addOnCompleteListener {
                liveData.value = it.isSuccessful
            }
        liveData
    }
}
