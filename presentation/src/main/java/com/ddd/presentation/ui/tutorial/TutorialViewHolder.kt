package com.ddd.presentation.ui.tutorial

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.fragment_frist_tutorial.view.*

class TutorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @SuppressLint("WrongConstant")
    fun bind(item: Triple<String, String, String>) {
        val (lottieFile, title, subTitle) = item
        with(itemView) {
            tv_title.text = title
            tv_sub_title.text = subTitle
            lottie.setAnimation(lottieFile)
            lottie.playAnimation()
        }
    }
}