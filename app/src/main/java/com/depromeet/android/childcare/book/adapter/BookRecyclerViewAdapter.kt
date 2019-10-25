package com.depromeet.android.childcare.book.adapter

import androidx.annotation.LayoutRes
import com.depromeet.android.childcare.databinding.ItemBookBinding
import com.depromeet.android.childcare.model.Record
import com.studyfirstproject.base.BaseRecyclerView

class BookRecyclerViewAdapter(
    @LayoutRes layoutResId: Int,
    bindingVariableId: Int
) : BaseRecyclerView<ItemBookBinding, Record>(layoutResId, bindingVariableId)