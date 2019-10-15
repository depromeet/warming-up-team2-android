package com.depromeet.android.childcare.bookdetail

import android.view.ViewGroup
import com.depromeet.android.childcare.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemBookDetailBinding
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseRecyclerView
import com.studyfirstproject.base.BaseViewHolder

class BookDetailListAdapter(
    private val navigator: BookDetailNavigator
): BaseRecyclerView<ItemBookDetailBinding, Record>(R.layout.activity_book_detail, BR.viewModel) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemBookDetailBinding, Record> {
        return BookDetailViewHolder(parent, navigator)
    }
}