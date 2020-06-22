package com.ddd.domain

import com.ddd.domain.entity.DomainEntity
import javax.inject.Inject

class CurriCulumUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    operator fun invoke(success: (List<DomainEntity.Curriculum>) -> Unit) {
        firebaseRepository.getCurriculum {
            it.let(success)
        }
    }
}