package com.ddd.presentation.ui.signup.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ddd.presentation.R
import com.ddd.presentation.databinding.FragmentStepFinalBinding
import com.ddd.presentation.ui.signup.SignUpActivity

class StepFinalFragment : Fragment() {
    companion object {
        fun getInstance(): StepFinalFragment {
            return StepFinalFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentStepFinalBinding>(
            inflater,
            R.layout.fragment_step_final,
            container,
            false
        ).apply {
            signUpViewModel = (activity as SignUpActivity).viewModel
        }.root
    }
}
