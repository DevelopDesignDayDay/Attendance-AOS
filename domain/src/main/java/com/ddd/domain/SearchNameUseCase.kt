package com.ddd.domain

import com.ddd.domain.entity.DomainEntity
import javax.inject.Inject

class SearchNameUseCase @Inject constructor(private val repository: FirebaseRepository) {

    operator fun invoke(name: String, getItems: (List<DomainEntity.UserEntity>) -> Unit) {
        repository.searchName(name, getItems)
    }
}