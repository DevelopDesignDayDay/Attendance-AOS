package com.ddd.presentation.ui.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.ddd.presentation.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_image_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class ImageDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        val url = FirebaseStorage.getInstance().getReference("banner/banner.png")
        try {
            Glide.with(this).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) = Unit

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    img.setImage(ImageSource.bitmap(resource))
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}