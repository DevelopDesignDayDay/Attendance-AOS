package com.ddd.presentation.ui.signup

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ddd.common.ob
import com.ddd.common.replaceFragment
import com.ddd.common.toast
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivitySignUpBinding
import com.ddd.presentation.ui.signup.step.StepAuthFragment
import com.ddd.presentation.ui.signup.step.StepPositionFragment
import javax.inject.Inject


class SignUpActivity : BaseActivity<SignUpViewModel, ActivitySignUpBinding>() {
    override val layoutResource: Int
        get() = R.layout.activity_sign_up
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: SignUpViewModel by lazy {
        ViewModelProviders.of(
            this,
            factory
        )[SignUpViewModel::class.java]
    }


    override fun setupViewDataBinding() {
        viewDataBinding.run {
            signUpViewModel = viewModel
            lifecycleOwner = this@SignUpActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ob(viewModel.liveResult, ::result)
    }

    private fun result(result: SignUpViewModel.Result) {
        when (result) {
            is SignUpViewModel.Result.Finish -> finish()
            is SignUpViewModel.Result.ToastMessage -> toast(result.msg)
            is SignUpViewModel.Result.ReplaceFragment -> {
                when (result.fragment) {
                    is StepAuthFragment, is StepPositionFragment -> hideKeyBoard()
                }
                replaceFragment(
                    supportFragmentManager,
                    R.id.frameLayout,
                    result.fragment
                )
            }
        }
    }
}
