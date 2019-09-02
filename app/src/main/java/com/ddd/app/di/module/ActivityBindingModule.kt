package com.ddd.app.di.module

import com.ddd.app.di.ViewModelBuilder
import com.ddd.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class, ViewModelBindModule::class])
    abstract fun bindMainActivity(): MainActivity
}
