package com.ddd.domain

import android.graphics.Bitmap
import com.ddd.common.DDDException
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import javax.inject.Inject

class GetQRCodeDataUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : DDDUseCase<Nothing, Bitmap>() {

    override fun execute(
        params: Nothing?,
        error: (DDDException) -> Unit,
        success: (Bitmap) -> Unit
    ) {
        (firebaseAuth.currentUser)?.let {
            success(BarcodeEncoder().encodeBitmap(it.uid, BarcodeFormat.QR_CODE, 400, 400))
        } ?: run {
            error(DDDException.ExpireSession)
        }
    }
}
