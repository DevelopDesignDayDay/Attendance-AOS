package com.ddd.common

sealed class DDDException : Exception() {
    object ExpireSession : DDDException()
}
