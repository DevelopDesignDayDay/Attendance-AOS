//package com.ddd.domain
//
//import com.ddd.common.DDDException
//import javax.inject.Inject
//
//class SelectedTimeSetUseCase @Inject constructor(
//    private val timeProviderRepository: TimeProviderRepository
//) : DDDUseCase<SelectedTimeSetUseCase.Params, String>() {
//    override fun execute(
//        params: Params?,
//        error: (DDDException) -> Unit,
//        success: (String) -> Unit
//    ) {
//        timeProviderRepository.onTimeSet(params.hour)
//    }
//    data class Params(val hour:Int,val min:Int)
//}
