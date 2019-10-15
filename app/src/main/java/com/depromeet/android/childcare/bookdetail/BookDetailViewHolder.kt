package com.depromeet.android.childcare.bookdetail

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ItemBookDetailBinding
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseViewHolder

class BookDetailViewHolder(
    parent: ViewGroup?,
    navigator: BookDetailNavigator,
    deviceWidth: Int
): BaseViewHolder<ItemBookDetailBinding, Record>(R.layout.item_book_detail, parent, BR.bookDetail) {

    init {
        binding.navigator = navigator
        itemView.layoutParams.width = (deviceWidth * 0.8).toInt()
        itemView.requestLayout()
    }
}