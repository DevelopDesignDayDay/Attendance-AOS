package com.ddd.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ddd.common.ob
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityMainBinding
import com.ddd.presentation.ui.main.adapter.CurriculumAdapter
import com.google.firebase.storage.FirebaseStorage
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

        ob(viewModel.liveResult, ::result)

        val url = FirebaseStorage.getInstance().getReference("banner/1_rM5eV-GbkiHgpD3MV-H6Hg.png")
        try{
            Glide.with(this).load(url).into(img_card)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun result(result: MainViewModel.Result) {
        when (result) {
            is MainViewModel.Result.Banner -> {
                tv_title.text = result.title
                tv_subtitle.text = result.subTitle
            }
            is MainViewModel.Result.Curriculum -> {
                recycler_calendar.adapter = CurriculumAdapter().apply {
                    setItems(result.items)
                }
            }
            is MainViewModel.Result.InitQRCode -> qr_img.setImageBitmap(result.qrcode)
            is MainViewModel.Result.LoginActivity<*> -> {
                Intent(this, result.nextActivity).let(::startActivity)
                finish()
            }
        }
    }
}
