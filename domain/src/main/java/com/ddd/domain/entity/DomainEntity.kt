package com.ddd.domain.entity

sealed class DomainEntity {
    data class Curriculum(val date: String?, val title: String?, val isDone: Boolean?) : DomainEntity()
}
