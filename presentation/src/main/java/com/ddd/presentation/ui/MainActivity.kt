package com.ddd.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutResource: Int = R.layout.activity_main
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, factory)[MainViewModel::class.java]
    }

    override fun setupViewDataBinding() {
        viewDataBinding.run {
            mainViewModel = viewModel
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("test", viewModel.toString())
    }
}
