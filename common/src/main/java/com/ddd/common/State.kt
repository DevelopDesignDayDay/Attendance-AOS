package com.ddd.common

sealed class State {
    data class Error(val exception: DDDException) : State()
    data class Loading(val isLoading: Boolean) : State()
    data class Data<T>(val data: T) : State()
}
