package com.depromeet.android.childcare.util.ext

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.depromeet.android.childcare.GlideApp

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

@BindingAdapter("bind_clip_to_out_line")
fun AppCompatImageView.bindClipToOutline(isClipToOutLine: Boolean) {
    clipToOutline = isClipToOutLine
}