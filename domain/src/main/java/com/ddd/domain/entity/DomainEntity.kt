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
}
