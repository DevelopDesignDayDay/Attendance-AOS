package com.ddd.presentation.ui.signup.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ddd.presentation.R
import com.ddd.presentation.databinding.FragmentStepAuthBinding
import com.ddd.presentation.ui.signup.SignUpActivity

class StepAuthFragment : Fragment() {

    companion object {
        fun getInstance(): StepAuthFragment {
            return StepAuthFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentStepAuthBinding>(
            inflater,
            R.layout.fragment_step_auth,
            container,
            false
        ).apply {
            signUpViewModel = (activity as SignUpActivity).viewModel
            lifecycleOwner = this@StepAuthFragment
            stepPositionFragment =
                StepPositionFragment.getInstance()
        }.root
    }
}
