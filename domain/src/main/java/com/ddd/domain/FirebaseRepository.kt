package com.ddd.domain

import com.ddd.common.DDDException

interface FirebaseRepository {
    fun saveUser(email: String, name: String, position: String, isManager: Boolean)
    fun isManager(uuid: String, result: (Boolean) -> Unit, error: (DDDException) -> Unit)
    fun saveAttendance(
        uuid: String,
        place:String,
        startAttendance: String,
        realAttendance: String,
        result: (Boolean) -> Unit,
        error: (DDDException) -> Unit
    )
}
