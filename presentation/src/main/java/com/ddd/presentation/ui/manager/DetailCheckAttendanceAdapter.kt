package com.ddd.presentation.ui.manager

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.domain.entity.DomainEntity
import com.ddd.presentation.R
import kotlinx.android.synthetic.main.item_detail_check_attendance.view.*
import java.text.SimpleDateFormat
import java.util.*

class DetailCheckAttendanceAdapter : RecyclerView.Adapter<DetailCheckAttendanceViewHolder>() {

    private val items = mutableListOf<DomainEntity.Attendance>()

    fun setItems(items: List<DomainEntity.Attendance>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCheckAttendanceViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_check_attendance, parent, false).let(::DetailCheckAttendanceViewHolder)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: DetailCheckAttendanceViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class DetailCheckAttendanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @SuppressLint("SetTextI18n")
    fun bind(entity: DomainEntity.Attendance) {
        with(itemView) {
            val result = if(entity.result=="0"){
                "출석"
            }else{
                "지각"
            }
            tv_message.text = "${SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN).format(Date(entity.realTime))}, $result"
        }
    }
}