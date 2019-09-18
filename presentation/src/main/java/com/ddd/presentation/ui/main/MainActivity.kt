package com.ddd.presentation.ui.main

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ddd.common.loadCircularImage
import com.ddd.domain.entity.DomainEntity
import com.ddd.presentation.BaseActivity
import com.ddd.presentation.R
import com.ddd.presentation.databinding.ActivityMainBinding
import com.ddd.presentation.ui.main.adapter.CurriculumAdapter
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_bottom_sheet.*
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

        generateQRCOde("아무개")


        val items = listOf(
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 1번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 2번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 3번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 4번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 5번째\n오리엔테이션", false),
            DomainEntity.Curriculum("October 12", "디디디 커리큘럼 6번째\n오리엔테이션", false)
        )
        recycler_calendar.adapter = CurriculumAdapter(items)
    }

    private fun generateQRCOde(contents: String) {
//        toBitmap(BarcodeEncoder().encode(contents, BarcodeFormat.QR_CODE, 30, 30))
//            .let(img_qr_code::setImageBitmap)

//        Glide.with(this)
//            .load(toBitmap(BarcodeEncoder().encode(contents, BarcodeFormat.QR_CODE, 30, 30)))
//            .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(this,30,2)))
//            .into(img_qr_code)
//        Glide.with(this)
//            .load(toBitmap(BarcodeEncoder().encode(contents, BarcodeFormat.QR_CODE, 30, 30)))
//            .apply(RequestOptions.circleCropTransform())
//            .into(img_qr_code)
//
        img_qr_code.loadCircularImage(
            toBitmap(BarcodeEncoder().encode(contents, BarcodeFormat.QR_CODE, 30, 30)),
            1f,
            Color.WHITE
        )
    }

    private fun toBitmap(matrix: BitMatrix): Bitmap {
        val height = matrix.height
        val width = matrix.width
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                val result =
                    if (matrix.get(x, y)) Color.WHITE else getColor(R.color.sign_up_selected_color)
                bmp.setPixel(x, y, result)
            }
        }
        return bmp
    }
}
