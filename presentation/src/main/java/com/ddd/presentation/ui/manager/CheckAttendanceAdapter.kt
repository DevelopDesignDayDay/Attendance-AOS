package com.ddd.presentation.ui.manager

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ddd.domain.entity.DomainEntity
import com.ddd.presentation.R
import kotlinx.android.synthetic.main.item_check_attendance.view.*
import java.text.SimpleDateFormat
import java.util.*

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
        holder.bind(items[position])
    }
}

class CheckAttendanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @SuppressLint("SetTextI18n")
    fun bind(entity: DomainEntity.UserEntity) {
        Log.e("entity", entity.attendance.count().toString())
        with(itemView) {
            val resource = when (entity.position) {
                "AOS" -> R.drawable.ic_sign_up_aos
                "iOS" -> R.drawable.ic_sign_up_ios
                "BackEnd" -> R.drawable.ic_sign_up_server
                else -> R.drawable.ic_sign_up_designer
            }
            img.background = ContextCompat.getDrawable(context, resource)
            tv_name.text = entity.name
            tv_position.text = entity.position
            tv_attendance.text = "${entity.attendance.count()} / 9"
            rv_attendance.adapter = DetailCheckAttendanceAdapter().apply {
                setItems(entity.attendance)
            }
        }
    }
}