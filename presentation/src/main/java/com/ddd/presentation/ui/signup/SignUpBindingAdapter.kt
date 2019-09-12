package com.ddd.presentation.ui.signup

import android.os.Build
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter(value = ["currentValue"])
fun ProgressBar.bindProgress(currentValue: Int) {
    this.setProgress(currentValue, true)
}