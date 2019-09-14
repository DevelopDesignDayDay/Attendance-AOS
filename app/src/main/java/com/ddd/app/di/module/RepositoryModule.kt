package com.ddd.app.di.module

import com.ddd.data.manager.FirebaseRepositoryImple
import com.ddd.domain.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun FirebaseRepository(
        databaseReference: DatabaseReference,
        firebaseAuth: FirebaseAuth
    ): FirebaseRepository = FirebaseRepositoryImple(databaseReference, firebaseAuth)
}