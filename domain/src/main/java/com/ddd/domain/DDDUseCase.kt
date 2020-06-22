package com.ddd.domain

import com.ddd.common.DDDException

abstract class DDDUseCase<P, R> {
    abstract fun execute(
        params: P? = null,
        error: (DDDException) -> Unit,
        success: (R) -> Unit
    )
}
