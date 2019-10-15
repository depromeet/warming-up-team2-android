package com.depromeet.android.childcare.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.depromeet.android.childcare.GlideApp
import com.depromeet.android.childcare.model.Record

@BindingAdapter("bind_feeds", "feed_navigator")
fun RecyclerView.setFeedItems(items : List<Record>, feedNavigator: FeedNavigator) {
    adapter as? FeedRecyclerViewAdapter ?: FeedRecyclerViewAdapter(feedNavigator).apply {
        adapter = this
    }.apply {
        setItems(items)
    }
}

@BindingAdapter("image_from_url")
fun AppCompatImageView.bindImageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(this.context)
            .load(imageUrl)
            .placeholder(ColorDrawable(Color.GRAY))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter("image_from_resId")
fun AppCompatImageView.bindImageFromResId(@DrawableRes resId: Int) {
    GlideApp.with(this.context)
        .load(resId)
        .placeholder(ColorDrawable(Color.GRAY))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}