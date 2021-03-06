package com.ddd.common

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
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

fun View.aniVisible() {
    val animation = AlphaAnimation(0f, 1f).apply { duration = 200 }
    this.visible()
    this.animation = animation
}

fun View.aniGone() {
    val animation = AlphaAnimation(1f, 0f).apply { duration = 200 }
    this.gone()
    this.animation = animation
}

fun Context.toast(mgs: CharSequence) = Toast.makeText(this, mgs, Toast.LENGTH_SHORT).show()
