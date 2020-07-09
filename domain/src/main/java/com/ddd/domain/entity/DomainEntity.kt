package com.ddd.domain.entity

import com.google.firebase.database.IgnoreExtraProperties

sealed class DomainEntity {
    @IgnoreExtraProperties
    data class Curriculum(
        val date: String = "",
        val isDone: Boolean = false,
        val title: String = ""
    ) : DomainEntity()

    data class Banner(
        val title: String,
        val subTitle: String
    ) : DomainEntity()

    @IgnoreExtraProperties
    data class UserEntity(
        val email: String = "",
        val name: String = "",
        val position: String = "",
        val isManager: Boolean = false,
        val attendance: List<Attendance>
    ) : DomainEntity()

    data class Attendance(
        val realTime: Long,
        val result: String = "1"
    ) : DomainEntity()
}
