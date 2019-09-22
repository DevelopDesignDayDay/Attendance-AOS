package com.ddd.domain

import com.ddd.common.DDDException
import javax.inject.Inject

class CheckManagerUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : DDDUseCas<String, Boolean>() {
    override fun execute(
        params: String?,
        error: (DDDException) -> Unit,
        success: (Boolean) -> Unit
    ) {
        firebaseRepository.isManager(params.orEmpty(),
            result = { success(it) },
            error = { error(it) })
    }
}