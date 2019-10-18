package com.depromeet.android.childcare.book.adapter

import androidx.annotation.LayoutRes
import com.depromeet.android.childcare.databinding.ItemSummaryBinding
import com.depromeet.android.childcare.model.Summary
import com.studyfirstproject.base.BaseRecyclerView

class SummaryRecyclerViewAdapter(
    @LayoutRes layoutResId: Int,
    bindingVariableId: Int
) : BaseRecyclerView<ItemSummaryBinding, Summary>(layoutResId, bindingVariableId)