package com.depromeet.android.childcare.util.ext

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.depromeet.android.childcare.GlideApp
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("image_from_url")
fun ImageView.bindImageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(this.context)
            .load(imageUrl)
            .placeholder(ColorDrawable(Color.GRAY))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

@BindingAdapter("image_from_url_with_blur", "blur_radius")
fun ImageView.bindImageFromUrlWithBlur(imageUrl: String?, radius: Int = 25) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(this.context)
            .load(imageUrl)
            .placeholder(ColorDrawable(Color.GRAY))
            .apply(RequestOptions.bitmapTransform(BlurTransformation(radius)))
            .into(this)
    }
}

@BindingAdapter("image_from_resId")
fun ImageView.bindImageFromResId(@DrawableRes resId: Int) {
    GlideApp.with(this.context)
        .load(resId)
        .placeholder(ColorDrawable(Color.GRAY))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("bind_clip_to_out_line")
fun ImageView.bindClipToOutline(isClipToOutLine: Boolean) {
    clipToOutline = isClipToOutLine
}