package com.depromeet.android.childcare.book

import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.depromeet.android.childcare.databinding.ItemSummaryBinding
import com.depromeet.android.childcare.model.Summary
import com.studyfirstproject.base.BaseViewHolder

class SummaryViewHolder(
    layoutResId: Int,
    parent: ViewGroup?,
    var selectedMonth: Int
) : BaseViewHolder<ItemSummaryBinding, Summary>(layoutResId, parent, BR.item)