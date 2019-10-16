package com.depromeet.android.childcare.bookdetail

import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BookDetailListItemDecoration : RecyclerView.ItemDecoration()  {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val basicPadding = getDps(4.5f, view.resources.displayMetrics)

        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                val offset = getDps(16f, view.resources.displayMetrics)
                outRect.left = offset
                outRect.right = basicPadding
            }
            state.itemCount - 1 -> {
                val offset = getDps(16f, view.resources.displayMetrics)
                outRect.left = basicPadding
                outRect.right = offset
            }
            else -> {
                outRect.left = basicPadding
                outRect.right = basicPadding
            }
        }
    }

    private fun getDps(dps: Float, metrics: DisplayMetrics): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, metrics).toInt()
}