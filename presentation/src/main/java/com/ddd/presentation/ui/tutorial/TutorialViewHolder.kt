package com.ddd.presentation.ui.tutorial

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_frist_tutorial.view.*

class TutorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Triple<Int, String, String>) {
        val (image, title, subTitle) = item
        with(itemView) {
            img_tutorial.background = ContextCompat.getDrawable(context, image)
            tv_title.text = title
            tv_sub_title.text = subTitle
        }
    }
}