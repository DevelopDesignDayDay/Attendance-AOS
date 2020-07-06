package com.ddd.domain

import com.ddd.common.DDDException
import com.ddd.domain.entity.DomainEntity

interface FirebaseRepository {
    fun signOut()
    fun saveUser(email: String, name: String, position: String, isManager: Boolean)
    fun isManager(uuid: String, result: (Boolean) -> Unit, error: (DDDException) -> Unit)
    fun saveAttendance(
        uuid: String,
        startAttendance: String,
        realAttendance: String,
        result: (Unit) -> Unit
    )
    fun getCurriculum(getItems:(List<DomainEntity.Curriculum>)->Unit)
    fun getBannerData(getItems: (DomainEntity.Banner) -> Unit)
    fun searchName(name: String,getItems: (List<DomainEntity.UserEntity>) -> Unit)
}
