package com.ddd.app.di.module

import com.ddd.data.TimeProviderImpl
import com.ddd.data.manager.FirebaseRepositoryImpl
import com.ddd.domain.FirebaseRepository
import com.ddd.domain.TimeProviderRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun firebaseRepository(
        databaseReference: DatabaseReference,
        firebaseAuth: FirebaseAuth
    ): FirebaseRepository = FirebaseRepositoryImpl(databaseReference, firebaseAuth)

    @Provides
    fun provideTimeProvider(): TimeProviderRepository = TimeProviderImpl()
}
