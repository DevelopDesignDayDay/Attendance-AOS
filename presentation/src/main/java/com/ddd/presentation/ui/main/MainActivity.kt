package com.ddd.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ddd.common.ob
import com.ddd.domain.entity.DomainEntity
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityMainBinding
import com.ddd.presentation.ui.main.adapter.CurriculumAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutResource: Int = R.layout.activity_main
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, factory)[MainViewModel::class.java]
    }

    override fun setupViewDataBinding() {
        viewDataBinding.run {
            mainViewModel = viewModel
            lifecycleOwner = this@MainActivity
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        img_temp.setOnClickListener {
            viewModel.tempLogout()
        }
        ob(viewModel.liveResult, ::result)


        val adapter = listOf(
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 1번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 2번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 3번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 4번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 5번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 6번째\n오리엔테이션", false)
        ).let(::CurriculumAdapter)
        recycler_calendar.adapter = adapter
    }

    fun result(result: MainViewModel.Result) {
        when (result) {
            is MainViewModel.Result.InitQRCode -> qr_img.setImageBitmap(result.qrcode)
            is MainViewModel.Result.LoginActivity<*> -> {
                startActivity(Intent(this, result.nextActivity))
                finish()
            }
        }
    }
}
