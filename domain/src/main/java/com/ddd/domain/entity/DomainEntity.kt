package com.ddd.domain.entity

sealed class DomainEntity {
    data class Curriculum(val date: String = "", val title: String = "") : DomainEntity()
    data class Banner(
        val title: String,
        val subTitle: String
    ) : DomainEntity()
}
