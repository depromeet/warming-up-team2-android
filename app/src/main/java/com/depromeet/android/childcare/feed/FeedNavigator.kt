package com.depromeet.android.childcare.feed

import android.view.View

interface FeedNavigator {
    fun showOptionDialog(feedId: Int, anchor: View)
    fun goFeedPictureActivity(imgUrl: String)
}