package com.ddd.data.manager

import com.ddd.data.entity.DataEntity
import com.ddd.domain.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseRepositoryImple @Inject constructor(
    private val db: DatabaseReference,
    private val auth: FirebaseAuth
) : FirebaseRepository {
    override fun saveUser(email: String, name: String, type: String, isManager: Boolean) {
        val user = DataEntity.UserEntity(email, name, type, isManager)
        db.child("users").child(auth.currentUser?.uid.orEmpty()).setValue(user)
    }
}