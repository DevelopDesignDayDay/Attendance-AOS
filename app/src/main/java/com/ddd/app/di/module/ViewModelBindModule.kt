package com.ddd.app.di.module

import androidx.lifecycle.ViewModel
import com.ddd.app.di.ViewModelKey
import com.ddd.presentation.ui.main.MainViewModel
import com.ddd.presentation.ui.manager.ManagerViewModel
import com.ddd.presentation.ui.manager.MoveCheckAttendanceViewModel
import com.ddd.presentation.ui.tutorial.login.LoginViewModel
import com.ddd.presentation.ui.signup.SignUpViewModel
import com.ddd.presentation.ui.splash.SplashViewModel
import com.ddd.presentation.ui.tutorial.TutorialViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewmodel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TutorialViewModel::class)
    abstract fun bindTutorialViewModel(tutorialViewModel: TutorialViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManagerViewModel::class)
    abstract fun bindManagerViewModel(managerViewModel: ManagerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoveCheckAttendanceViewModel::class)
    abstract fun bindMoveCheckAttendanceViewModel(managerViewModel: MoveCheckAttendanceViewModel): ViewModel
}
