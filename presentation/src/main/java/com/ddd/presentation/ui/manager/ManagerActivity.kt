package com.ddd.presentation.ui.manager

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ddd.common.createViewModel
import com.ddd.common.view.TimePickerFragment
import com.ddd.common.view.TimePickerSelected
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityManagerBinding
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_manager.*
import javax.inject.Inject

class ManagerActivity : BaseActivity<ManagerViewModel, ActivityManagerBinding>(),
    TimePickerSelected {

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
            lifecycleOwner=this@ManagerActivity
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btn_start_qr_code.setOnClickListener {
            IntentIntegrator(this)
                .apply { setOrientationLocked(false) }
                .initiateScan()
        }

        tv_attend_start_time.setOnClickListener {
            TimePickerFragment(this).show(supportFragmentManager, TimePickerFragment::getTag.name)
        }
    }

    override fun onTimeSet(hour: Int, min: Int) = viewModel.onTimeSet(hour, min)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        (IntentIntegrator.parseActivityResult(requestCode, resultCode, data))?.let { result ->
            if (!result.contents.isNullOrEmpty()) viewModel.addAttendance(result.contents)
        } ?: super.onActivityResult(requestCode, resultCode, data)
    }
}
