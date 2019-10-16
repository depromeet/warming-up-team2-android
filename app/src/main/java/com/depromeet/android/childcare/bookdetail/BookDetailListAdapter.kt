package com.depromeet.android.childcare.bookdetail

import android.view.ViewGroup
import com.depromeet.android.childcare.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemBookDetailBinding
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseRecyclerView
import com.studyfirstproject.base.BaseViewHolder

class BookDetailListAdapter(
    private val navigator: BookDetailNavigator,
    private val deviceWidth: Int
): BaseRecyclerView<ItemBookDetailBinding, BookDetailItemViewModel>(R.layout.item_book_detail, BR.viewModel) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemBookDetailBinding, BookDetailItemViewModel> {
        return super.onCreateViewHolder(parent, viewType).apply {
            itemView.layoutParams.width = (deviceWidth * 0.76).toInt()
            itemView.requestLayout()
        }
    }

    fun setWrapperItems(data: List<Record>) {
        items.clear()
        items.addAll(data.map {
            BookDetailItemViewModel(it, navigator)
        })
        notifyDataSetChanged()
    }
}