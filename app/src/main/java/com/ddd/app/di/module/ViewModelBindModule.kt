package com.ddd.app.di.module

import androidx.lifecycle.ViewModel
import com.ddd.app.di.ViewModelKey
import com.ddd.presentation.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewmodel(mainViewModel: MainViewModel): ViewModel
}
