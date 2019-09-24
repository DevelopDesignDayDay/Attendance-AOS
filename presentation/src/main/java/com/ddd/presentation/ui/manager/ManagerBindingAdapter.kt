package com.ddd.presentation.ui.manager

import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["place", "startAttendance"])
fun Button.bindEnable(place: String?, startAttendance: String?) {
    this.isEnabled = (!place.isNullOrEmpty() && !startAttendance.isNullOrEmpty())
}
