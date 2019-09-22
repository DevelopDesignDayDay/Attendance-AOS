package com.ddd.domain

import androidx.lifecycle.LiveData
import com.ddd.common.State

abstract class DDDLiveDataUseCase<P, R : LiveData<State>> {
    abstract fun execute(params: P? = null, error: (String) -> Unit): R
}
