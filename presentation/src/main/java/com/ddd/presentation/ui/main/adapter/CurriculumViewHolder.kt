package com.ddd.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ddd.domain.entity.DomainEntity
import com.ddd.presentation.R
import kotlinx.android.synthetic.main.item_curriculum.view.*

class CurriculumViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_curriculum, parent, false)
) {
    fun onBind(curriculum: DomainEntity.Curriculum) {
        with(itemView) {
            tv_calendar.text = curriculum.date
            tv_title.text = curriculum.title
            val res = if (curriculum.isDone) {
                R.drawable.ic_check
            } else {
                R.drawable.ic_check_off
            }
            img_status.background = ContextCompat.getDrawable(context, res)
        }
    }
}
