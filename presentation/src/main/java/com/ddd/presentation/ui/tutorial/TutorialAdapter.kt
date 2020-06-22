package com.ddd.presentation.ui.tutorial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.presentation.R

class TutorialAdapter(
    private val items: List<Triple<Int, String, String>>
) : RecyclerView.Adapter<TutorialViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_frist_tutorial, parent, false)
            .let(::TutorialViewHolder)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) =
        holder.bind(items[position])
}