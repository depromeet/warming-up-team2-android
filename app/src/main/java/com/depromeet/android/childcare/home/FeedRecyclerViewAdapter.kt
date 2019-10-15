package com.depromeet.android.childcare.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.model.Record

class FeedRecyclerViewAdapter(
    private val feedNavigator: FeedNavigator
): RecyclerView.Adapter<FeedViewHolder>() {
    private val feedList = mutableListOf<Record>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FeedViewHolder(parent, feedNavigator)

    override fun getItemCount(): Int = feedList.size

    fun getItem(position: Int) = feedList[position]

    fun setItems(data: List<Record>) {
        feedList.clear()
        feedList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}