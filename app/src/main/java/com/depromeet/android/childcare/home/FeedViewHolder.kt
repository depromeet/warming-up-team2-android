package com.depromeet.android.childcare.home

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemFeedBinding
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseViewHolder

class FeedViewHolder(
    parent: ViewGroup?,
    private val navigator: FeedNavigator
): BaseViewHolder<ItemFeedBinding, Record>(R.layout.item_feed, parent, BR.feed) {

    init {
        binding.apply {
            this.navigator = this@FeedViewHolder.navigator
        }
    }
}