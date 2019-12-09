package com.depromeet.android.childcare.feed

import android.view.View
import com.depromeet.android.childcare.model.Record

interface FeedNavigator {
    fun showOptionDialog(feed: Record, anchor: View)
    fun goFeedPictureActivity(imgUrl: String?)
}