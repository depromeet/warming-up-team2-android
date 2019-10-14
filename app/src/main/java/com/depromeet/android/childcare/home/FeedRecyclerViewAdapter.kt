package com.depromeet.android.childcare.home

import androidx.databinding.library.baseAdapters.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemFeedBinding
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseRecyclerView

class FeedRecyclerViewAdapter(
    feedNavigator: FeedNavigator
): BaseRecyclerView<ItemFeedBinding, Record>(R.layout.item_feed, BR.feed) {

}