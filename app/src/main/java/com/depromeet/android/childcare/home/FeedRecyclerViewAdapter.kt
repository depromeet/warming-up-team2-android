package com.depromeet.android.childcare.home

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemFeedBinding
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseRecyclerView
import com.studyfirstproject.base.BaseViewHolder

class FeedRecyclerViewAdapter(
    private val feedNavigator: FeedNavigator
): BaseRecyclerView<ItemFeedBinding, Record>(R.layout.fragment_home, BR.viewModel) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemFeedBinding, Record> {
        return FeedViewHolder(parent, feedNavigator)
    }
}