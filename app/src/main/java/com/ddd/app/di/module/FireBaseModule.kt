package com.ddd.app.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class FireBaseModule {

    @Provides
    fun getAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun getFireBaseDataBase(): DatabaseReference = FirebaseDatabase.getInstance().reference

}