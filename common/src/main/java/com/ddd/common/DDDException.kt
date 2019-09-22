package com.ddd.common

sealed class DDDException : Exception() {
    //Login
    object ExpireSession : DDDException()

    //FireBase
    data class NotFindDataBaseData(val msg: String) : DDDException()
}
