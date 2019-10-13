package com.depromeet.android.childcare.book

import androidx.lifecycle.ViewModelProviders
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentBookBinding


class BookFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {
    val viewModel by lazy { ViewModelProviders.of(this).get(BookViewModel::class.java) }
}