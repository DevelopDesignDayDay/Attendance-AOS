package com.ddd.domain

import com.ddd.common.DDDException
import javax.inject.Inject

class AddAttendanceUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
):DDDUseCase<AddAttendanceUseCase.Params,Unit>(){
    override fun execute(params: Params?, error: (DDDException) -> Unit, success: (Unit) -> Unit) {
        params?.let {
            with(it){
                firebaseRepository.saveAttendance(uuid,place,startAttendance,realAttendance,success)
            }
        }
    }

    data class Params(val uuid:String,val place:String,val startAttendance:String,val realAttendance:String)
}
