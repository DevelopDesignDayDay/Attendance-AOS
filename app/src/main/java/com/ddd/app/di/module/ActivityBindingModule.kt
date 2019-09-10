package com.ddd.app.di.module

import com.ddd.app.di.ViewModelBuilder
import com.ddd.presentation.ui.MainActivity
import com.ddd.presentation.ui.signup.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelBuilder::class, ViewModelBindModule::class])
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSignUpActivity(): SignUpActivity
}
