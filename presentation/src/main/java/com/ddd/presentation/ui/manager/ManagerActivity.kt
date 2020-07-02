package com.ddd.presentation.ui.manager

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ddd.common.createViewModel
import com.ddd.common.ob
import com.ddd.common.view.TimePickerFragment
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityManagerBinding
import com.ddd.presentation.ui.signup.SignUpActivity
import com.ddd.presentation.ui.tutorial.TutorialActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_manager.*
import javax.inject.Inject

class ManagerActivity : BaseActivity<ManagerViewModel, ActivityManagerBinding>() {

    override val layoutResource: Int
        get() = R.layout.activity_manager

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: ManagerViewModel by lazy {
        createViewModel(factory, ManagerViewModel::class.java)
    }

    override fun setupViewDataBinding() {
        viewDataBinding.run {
            managerViewModel = viewModel
            lifecycleOwner = this@ManagerActivity
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ob(viewModel.liveNavigation, result)
        btn_start_qr_code.setOnClickListener {
            IntentIntegrator(this)
                .apply { setOrientationLocked(false) }
                .initiateScan()
        }

        tv_attend_start_time.setOnClickListener {
            TimePickerFragment(viewModel::onTimeSet)
                .show(supportFragmentManager, TimePickerFragment::getTag.name)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        (IntentIntegrator.parseActivityResult(requestCode, resultCode, data))?.let { result ->
            if (!result.contents.isNullOrEmpty()) viewModel.addAttendance(result.contents)
        } ?: super.onActivityResult(requestCode, resultCode, data)
    }

    private val result: (ManagerViewModel.Navigation) -> Unit = {
        when (it) {
            is ManagerViewModel.Navigation.MoveCheckAttendance -> {

            }

            is ManagerViewModel.Navigation.MoveLoginActivity -> {
                Intent(this, TutorialActivity::class.java).let(::startActivity)
                finish()
            }
        }
    }
}
