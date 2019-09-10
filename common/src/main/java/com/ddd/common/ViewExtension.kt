package com.ddd.common

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun ViewGroup.recyclerViewInflate(@LayoutRes layout: Int, attach: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layout, this, attach)
}

fun Activity.replaceFragment(manager: FragmentManager, @IdRes resource: Int, fragment: Fragment) {
    manager.beginTransaction()
        .replace(resource, fragment)
        .commit()
}

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
