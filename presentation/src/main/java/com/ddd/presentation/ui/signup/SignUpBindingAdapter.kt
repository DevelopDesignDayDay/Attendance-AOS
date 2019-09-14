package com.ddd.presentation.ui.signup

import android.os.Build
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter(value = ["currentValue"])
fun ProgressBar.bindProgress(currentValue: Int) {
    this.setProgress(currentValue, true)
}

@BindingAdapter(value = ["firstInput", "lastInput"])
fun Button.bindNextButton(firstInput: String?, lastInput: String?) {
    this.isEnabled = !firstInput.isNullOrEmpty() && !lastInput.isNullOrEmpty()
}

@BindingAdapter(value = ["resultPosition"])
fun Button.bindSelectedPositionNextButton(resultPosition: SignUpViewModel.Result?) {
    if (resultPosition is SignUpViewModel.Result.SelectedPosition?) this.isEnabled = resultPosition != null
}