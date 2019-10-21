package com.depromeet.android.childcare.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentFeedBinding
import com.depromeet.android.childcare.feed.feeddetail.FeedDetailItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel


class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed), FeedNavigator {

    private val feedViewModel: FeedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.apply {
            viewModel = feedViewModel
            rvFeedList.adapter = FeedRecyclerViewAdapter(this@FeedFragment)
            rvFeedList.addItemDecoration(FeedDetailItemDecoration())
        }

        return binding.root
    }

    override fun showOptionDialog(feedId: Int) {
        showToast("show dialog with id $feedId")
    }
}