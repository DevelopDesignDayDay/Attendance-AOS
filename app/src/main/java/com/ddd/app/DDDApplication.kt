package com.ddd.app

import com.ddd.app.di.DaggerDDDComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DDDApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerDDDComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
    }
}
