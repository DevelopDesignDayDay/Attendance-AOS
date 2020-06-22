package com.ddd.presentation.ui.tutorial.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ddd.presentation.R
import com.ddd.presentation.databinding.BottomSheetLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LoginBottomSheetDialog(
    private val viewModel: LoginViewModel
) : BottomSheetDialogFragment() {
    companion object {
        fun getInstance(loginViewModel: LoginViewModel): LoginBottomSheetDialog {
            return LoginBottomSheetDialog(loginViewModel)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<BottomSheetLoginBinding>(
            inflater,
            R.layout.bottom_sheet_login,
            container,
            false
        ).apply {
            loginViewModel = viewModel
            lifecycleOwner = this@LoginBottomSheetDialog
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }
}