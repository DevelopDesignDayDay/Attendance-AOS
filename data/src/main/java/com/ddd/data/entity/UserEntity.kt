package com.ddd.data.entity

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

sealed class DataEntity {
    @IgnoreExtraProperties
    data class UserEntity(
        val email: String = "",
        val name: String = "",
        val position: String = "",
        val isManager: Boolean = false
    ) : DataEntity() {
        @Exclude
        fun toMap(): Map<String, Any?> {
            return mapOf(
                "email" to email,
                "isManager" to isManager,
                "name" to name,
                "position" to position
            )
        }
    }

    @IgnoreExtraProperties
    data class Attendance(
        val result: String
    ) : DataEntity()
}
