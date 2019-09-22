package com.ddd.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ddd.common.createViewModel
import com.ddd.common.ob
import com.ddd.common.toast
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivitySplashBinding
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val layoutResource: Int = R.layout.activity_splash
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: SplashViewModel by lazy {
        createViewModel(factory, SplashViewModel::class.java)
    }

    override fun setupViewDataBinding() {
        viewDataBinding.run {
            splashViewModel = viewModel
            lifecycleOwner = this@SplashActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isLoginUser()
        ob(viewModel.liveResult, ::result)
    }

    fun result(result: SplashViewModel.Result) {
        when (result) {
            is SplashViewModel.Result.Error -> toast(result.msg)
            is SplashViewModel.Result.SuccessSignUp<*> -> {
                startActivity(Intent(this, result.nextActivity.java))
                finish()
            }
        }
    }
}
