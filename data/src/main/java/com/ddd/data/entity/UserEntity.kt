package com.ddd.data.entity

sealed class DataEntity {
    data class UserEntity(
        val email: String = "",
        val name: String = "",
        val position: String = "",
        val isManager: Boolean = false
    ) : DataEntity()
}