package com.ddd.presentation.ui.signup

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.ddd.common.ob
import com.ddd.common.replaceFragment
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity<SignUpViewModel, ActivitySignUpBinding>() {
    override val layoutResource: Int
        get() = R.layout.activity_sign_up
    override val viewModel: SignUpViewModel by lazy { ViewModelProviders.of(this)[SignUpViewModel::class.java] }


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
            is SignUpViewModel.Result.ReplaceFragment -> {
                Log.e("??", result.fragment.toString())
                replaceFragment(
                    supportFragmentManager,
                    R.id.frameLayout,
                    result.fragment
                )
            }
        }
    }
}
