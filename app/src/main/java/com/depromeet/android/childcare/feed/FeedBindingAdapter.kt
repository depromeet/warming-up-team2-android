package com.depromeet.android.childcare.feed

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.model.PaymentType
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.util.convertToString
import com.depromeet.android.childcare.util.toDate

@BindingAdapter("bind_feed_type")
fun RecyclerView.setBookDetailsItems(feedType: FeedType) {
    val feedLayoutManager = layoutManager as GridLayoutManager
    when(feedType) {
        FeedType.DETAIL -> {
            feedLayoutManager.spanCount = 1
        }
        FeedType.SUMMARY -> {
            feedLayoutManager.spanCount = 2
        }
    }
    (adapter as FeedRecyclerViewAdapter).run {
        this.feedType = feedType
        notifyDataSetChanged()
    }
}

@BindingAdapter("bind_feed_items")
fun RecyclerView.setFeedItems(items : List<Record>) {
    (adapter as FeedRecyclerViewAdapter).setItems(items)
}

@BindingAdapter("bind_payment_type")
fun TextView.setPaymentType(paymentType: PaymentType) {
    text = when(paymentType) {
        PaymentType.CARD -> resources.getString(R.string.feed_payment_card)
        PaymentType.CASH -> resources.getString(R.string.feed_payment_cash)
    }
}

@BindingAdapter("bind_feed_date")
fun TextView.setFeedDate(datePattern: String) {
    datePattern.toDate("yyyy-MM-dd")?.let {
        text = it.convertToString("yyyy.MM.dd")
    }
}