package com.ddd.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {

    fun tempLogout() {
        auth.signOut()
    }

}
