package com.depromeet.android.childcare.feed.feeddetail

import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FeedDetailItemDecoration : RecyclerView.ItemDecoration()  {

    var bottomPadding: Int? = null

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

//        bottomPadding?.let {
//            outRect.bottom = it
//        }  ?: run {
//            this.bottomPadding = getDps(32f, view.resources.displayMetrics)
//            outRect.bottom = this.bottomPadding ?: 0
//        }

    }

    private fun getDps(dps: Float, metrics: DisplayMetrics): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, metrics).toInt()
}