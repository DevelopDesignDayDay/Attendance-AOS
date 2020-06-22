package com.ddd.app.di.module

import android.content.Context
import com.ddd.app.DDDApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: DDDApplication): Context = app.applicationContext

}