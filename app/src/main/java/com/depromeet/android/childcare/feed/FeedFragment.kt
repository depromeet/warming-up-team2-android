package com.depromeet.android.childcare.feed

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.EDIT_ITEM
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentFeedBinding
import com.depromeet.android.childcare.editbook.EditBookActivity
import com.depromeet.android.childcare.feed.feedpicture.FeedPictureActivity
import com.depromeet.android.childcare.main.ChangeRecordsEventBus
import com.depromeet.android.childcare.model.Record
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FeedFragment : BaseFragment<FragmentFeedBinding>(R.layout.fragment_feed), FeedNavigator {

    private val feedViewModel: FeedViewModel by viewModel()
    private val changeRecordsEventBus: ChangeRecordsEventBus by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel = feedViewModel
            rvFeedList.adapter =
                FeedRecyclerViewAdapter(this@FeedFragment)
        }

        changeRecordsEventBus.changeRecordsEvent.observe(this, Observer {
            feedViewModel.refreshFeeds()
        })
    }

    private fun goEditBookActivity() {
        activity?.let {
            val intent = EditBookActivity.getStartIntent(it)
            it.startActivityForResult(intent, EDIT_ITEM)
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
                activity.startActivity(FeedPictureActivity.getStartIntent(activity, it))
            }
        }
    }
}