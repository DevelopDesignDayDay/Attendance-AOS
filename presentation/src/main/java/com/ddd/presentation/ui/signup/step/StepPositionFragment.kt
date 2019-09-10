package com.ddd.presentation.ui.signup.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ddd.presentation.R
import com.ddd.presentation.databinding.FragmentStepPositionBinding
import com.ddd.presentation.ui.signup.SignUpActivity

class StepPositionFragment : Fragment() {

    companion object {
        fun getInstance(): StepPositionFragment {
            return StepPositionFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentStepPositionBinding>(
            inflater,
            R.layout.fragment_step_position,
            container,
            false
        ).apply {
            signUpViewModel = (activity as SignUpActivity).viewModel
            stepFinalFragment =
                StepFinalFragment.getInstance()
        }.root
    }
}
