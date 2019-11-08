package com.depromeet.android.childcare.feed

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.feed.feeddetail.FeedDetailViewHolder
import com.depromeet.android.childcare.feed.feedsummary.FeedSummaryViewHolder
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseViewHolder

class FeedRecyclerViewAdapter(
    private val feedNavigator: FeedNavigator
): RecyclerView.Adapter<BaseViewHolder<*, Record>>() {

    private val items = mutableListOf<Record>()

    var feedType = FeedType.DETAIL

    override fun getItemViewType(position: Int): Int {
        return if (feedType == FeedType.DETAIL) {
            FeedType.DETAIL.ordinal
        } else {
            FeedType.SUMMARY.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, Record> {
        return if (viewType == FeedType.DETAIL.ordinal) FeedDetailViewHolder(parent, feedNavigator)
        else FeedSummaryViewHolder(parent, feedNavigator)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<*, Record>, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItem(position: Int) = items[position]

    fun setItems(data: List<Record>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}