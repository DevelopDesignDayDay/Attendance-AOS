package com.ddd.presentation.ui.manager

import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["startAttendance"])
fun Button.bindEnable(startAttendance: String?) {
    this.isEnabled = !startAttendance.isNullOrEmpty()
}
