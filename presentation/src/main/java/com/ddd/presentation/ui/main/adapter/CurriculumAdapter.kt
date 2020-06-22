package com.ddd.presentation.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.domain.entity.DomainEntity

class CurriculumAdapter :
    RecyclerView.Adapter<CurriculumViewHolder>() {

    private var itemList: List<DomainEntity.Curriculum> = emptyList()

    fun setItems(curriculumItems: List<DomainEntity.Curriculum>) {
        this.itemList = curriculumItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurriculumViewHolder {
        return CurriculumViewHolder(parent)
    }

    override fun getItemCount(): Int = itemList.count()

    override fun onBindViewHolder(holder: CurriculumViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }
}
