package com.ddd.domain

interface FirebaseRepository {
    fun saveUser(email: String, name: String, type: String,isManager:Boolean)
}