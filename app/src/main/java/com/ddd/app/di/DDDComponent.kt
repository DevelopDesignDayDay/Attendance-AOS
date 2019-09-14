package com.ddd.app.di

import android.content.Context
import com.ddd.app.DDDApplication
import com.ddd.app.di.module.ActivityBindingModule
import com.ddd.app.di.module.AppModule
import com.ddd.app.di.module.FireBaseModule
import com.ddd.app.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        FireBaseModule::class,
        RepositoryModule::class
    ]
)
interface DDDComponent : AndroidInjector<DDDApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): DDDComponent
    }
}
