package com.ddd.presentation.ui.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ddd.common.createViewModel
import com.ddd.common.ob
import com.ddd.common.toast
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityTutorialBinding
import com.ddd.presentation.ui.tutorial.login.LoginBottomSheetDialog
import com.ddd.presentation.ui.tutorial.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_tutorial.*
import javax.inject.Inject

const val FIRST_INDEX = 0

class TutorialActivity : BaseActivity<TutorialViewModel, ActivityTutorialBinding>() {
    override val layoutResource: Int = R.layout.activity_tutorial
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: TutorialViewModel by lazy {
        createViewModel(factory, TutorialViewModel::class.java)
    }
    val loginViewModel by lazy { createViewModel(factory, LoginViewModel::class.java) }

    override fun setupViewDataBinding() {
        viewDataBinding.run {
            bindingTutorialViewModel = viewModel
            bindingLoginViewModel = loginViewModel
            lifecycleOwner = this@TutorialActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createTutorialDot()
        ob(loginViewModel.liveResult, ::loginResult)
        ob(viewModel.liveResult, ::tutorialResult)
    }

    private fun tutorialResult(result: TutorialViewModel.Result) {
        when (result) {
            is TutorialViewModel.Result.MoveSignUp<*> -> {
                startActivity(Intent(this, result.activity.java))
            }
        }
    }

    private fun loginResult(result: LoginViewModel.Result) {
        when (result) {
            is LoginViewModel.Result.SuccessSignUp<*> -> {
                startActivity(Intent(this, result.nextActivity.java))
                finish()
            }
            is LoginViewModel.Result.ShowLoginBottomSheet -> {
                result.loginBottomSheet.show(
                    supportFragmentManager,
                    LoginBottomSheetDialog::class.java.name
                )
            }
            is LoginViewModel.Result.Error -> toast(result.msg)
        }
    }

    private fun createTutorialDot() {
        circleIndicator.createDot(
            3,
            R.drawable.tutorial_indicator_dot_off,
            R.drawable.tutorial_indicator_dot_on,
            FIRST_INDEX
        )
    }
}
