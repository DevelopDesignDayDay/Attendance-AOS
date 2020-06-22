package com.ddd.domain

import com.ddd.domain.entity.DomainEntity
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    operator fun invoke(success: (DomainEntity.Banner) -> Unit){
        firebaseRepository.getBannerData(success)
    }
}