package com.ddd.presentation.ui.signup.step

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ddd.common.ob
import com.ddd.presentation.R
import com.ddd.presentation.databinding.FragmentStepPositionBinding
import com.ddd.presentation.ui.signup.Position
import com.ddd.presentation.ui.signup.SignUpActivity
import com.ddd.presentation.ui.signup.SignUpViewModel
import kotlinx.android.synthetic.main.fragment_step_position.*

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
            lifecycleOwner = this@StepPositionFragment
            designer = Position.Designer
            aos = Position.AOS
            ios = Position.iOS
            backEnd = Position.BackEnd
            stepFinalFragment =
                StepFinalFragment.getInstance()
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ob((activity as SignUpActivity).viewModel.liveResult, ::result)

    }

    private fun allCheckUI() {
        if (custom_container_designer.isVisible) custom_container_designer.initialize()
        if (custom_container_aos.isVisible) custom_container_aos.initialize()
        if (custom_container_ios.isVisible) custom_container_ios.initialize()
        if (custom_container_backEnd.isVisible) custom_container_backEnd.initialize()
    }

    private val setChecked: (position: Position, checkAllVisible: () -> Unit) -> Unit =
        { position, checkAllVisible ->
            checkAllVisible()
            when (position) {
                Position.Designer -> custom_container_designer.setChecked()
                Position.AOS -> custom_container_aos.setChecked()
                Position.iOS -> custom_container_ios.setChecked()
                Position.BackEnd -> custom_container_backEnd.setChecked()
            }
        }

    private fun result(result: SignUpViewModel.Result) {
        when (result) {
            is SignUpViewModel.Result.SelectedPosition -> setChecked(
                result.position,
                ::allCheckUI
            )
        }
    }
}
