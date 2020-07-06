package com.ddd.presentation.ui.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.domain.entity.DomainEntity
import com.ddd.presentation.R
import kotlinx.android.synthetic.main.item_check_attendance.view.*

class CheckAttendanceAdapter : RecyclerView.Adapter<CheckAttendanceViewHolder>() {

    private val items = mutableListOf<DomainEntity.UserEntity>()

    fun setItems(items: List<DomainEntity.UserEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckAttendanceViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_check_attendance, parent, false).let(::CheckAttendanceViewHolder)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: CheckAttendanceViewHolder, position: Int) {
        holder.onbind(items[position])
    }
}

class CheckAttendanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onbind(entity: DomainEntity.UserEntity) {
        with(itemView) {
            tv_name.text = entity.name
            tv_position.text = entity.position
        }
    }
}