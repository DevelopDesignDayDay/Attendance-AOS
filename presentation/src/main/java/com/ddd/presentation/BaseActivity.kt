package com.ddd.presentation

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<VM : ViewModel, B : ViewDataBinding> : DaggerAppCompatActivity() {
    abstract val layoutResource: Int
    abstract val viewModel: VM
    lateinit var viewDataBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResource)
        setupViewDataBinding()
    }

    abstract fun setupViewDataBinding()

    fun hideKeyBoard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}
