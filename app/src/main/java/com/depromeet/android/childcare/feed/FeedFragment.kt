package com.depromeet.android.childcare.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentFeedBinding
import com.depromeet.android.childcare.editbook.EditBookActivity
import com.depromeet.android.childcare.feed.feedpicture.FeedPictureActivity
import com.depromeet.android.childcare.model.Record
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
            rvFeedList.adapter =
                FeedRecyclerViewAdapter(this@FeedFragment)
        }

        return binding.root
    }

    private fun goEditBookActivity() {
        activity?.let {
            val intent = EditBookActivity.getStartIntent(it)
            startActivity(intent)
        }
    }

    override fun showOptionDialog(feed: Record, anchor: View) {
        activity?.let {
            val popup = PopupMenu(it, anchor)
            popup.menuInflater.inflate(R.menu.menu_feed_option, popup.menu)

            popup.setOnMenuItemClickListener {menuItem ->
                when(menuItem.itemId) {
                    R.id.menu_feed_edit -> {
                        feedViewModel.onEditBookClick(feed)
                        goEditBookActivity()
                    }
                    R.id.menu_feed_delete -> showToast("delete with feedId ${feed.id}")
                }
                true
            }

            popup.show()
        }
    }

    override fun goFeedPictureActivity(imgUrl: String?) {
        activity?.let {activity ->
            imgUrl?.let {
                startActivity(FeedPictureActivity.getStartIntent(activity, it))
            }
        }
    }
}