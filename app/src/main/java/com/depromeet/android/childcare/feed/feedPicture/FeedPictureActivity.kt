package com.depromeet.android.childcare.feed.feedPicture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.android.childcare.EXTRA_IMG_URL
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityFeedPictureBinding
import com.depromeet.android.childcare.util.ext.bindImageFromUrl
import com.studyfirstproject.base.BaseActivity

// 간단해서 일단 viewmodel 을 만들지는 않음
class FeedPictureActivity : BaseActivity<ActivityFeedPictureBinding>(R.layout.activity_feed_picture) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imgUrl = intent.getStringExtra(EXTRA_IMG_URL)

        initView(imgUrl)
    }

    fun initView(imgUrl: String?) {
        binding.ivBackToFeed.setOnClickListener {
            finish()
        }

        binding.ivOriginalFeedImage
            .bindImageFromUrl(imgUrl)
    }

    companion object {

        fun getStartIntent(context: Context, imgUrl: String): Intent =
            Intent(context, FeedPictureActivity::class.java).apply {
                putExtra(EXTRA_IMG_URL, imgUrl)
            }
    }
}
