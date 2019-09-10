package com.ddd.presentation.ui.signup.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ddd.presentation.R
import com.ddd.presentation.databinding.FragmentStepNameBinding
import com.ddd.presentation.ui.signup.SignUpActivity

class StepNameFragment : Fragment() {
    companion object {
        fun getInstance(): StepNameFragment {
            return StepNameFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentStepNameBinding>(
            inflater,
            R.layout.fragment_step_name,
            container,
            false
        ).apply {
            signUpViewModel = (activity as SignUpActivity).viewModel
            stepAuthFragment =
                StepAuthFragment.getInstance()
        }.root
    }
}
