package com.ddd.presentation

import android.os.Bundle
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
}
