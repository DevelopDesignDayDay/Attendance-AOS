package com.ddd.app.di

import android.content.Context
import com.ddd.app.DDDApplication
import com.ddd.app.di.module.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class
    ]
)
interface DDDComponent : AndroidInjector<DDDApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): DDDComponent
    }
}
