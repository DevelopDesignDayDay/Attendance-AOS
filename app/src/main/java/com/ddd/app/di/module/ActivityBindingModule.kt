package com.ddd.app.di.module

import com.ddd.app.di.ViewModelBuilder
import com.ddd.presentation.ui.main.MainActivity
import com.ddd.presentation.ui.manager.ManagerActivity
import com.ddd.presentation.ui.manager.MoveCheckAttendance
import com.ddd.presentation.ui.signup.SignUpActivity
import com.ddd.presentation.ui.splash.SplashActivity
import com.ddd.presentation.ui.tutorial.TutorialActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelBuilder::class, ViewModelBindModule::class])
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindTutorialActivity(): TutorialActivity

    @ContributesAndroidInjector
    abstract fun bindManagerActivity(): ManagerActivity

    @ContributesAndroidInjector
    abstract fun bindMoveCheckAttendance (): MoveCheckAttendance
}
