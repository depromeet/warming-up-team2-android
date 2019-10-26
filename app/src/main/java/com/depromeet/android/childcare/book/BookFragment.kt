package com.depromeet.android.childcare.book

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.book.adapter.BookRecyclerViewAdapter
import com.depromeet.android.childcare.book.adapter.SummaryRecyclerViewAdapter
import com.depromeet.android.childcare.databinding.FragmentBookBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {
    private val bookViewModel: BookViewModel by viewModel()
    private val summaryAdapter = SummaryRecyclerViewAdapter(R.layout.item_summary, BR.item)
    private val recordAdapter = BookRecyclerViewAdapter(R.layout.item_book, BR.item)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvBookSummary)

        with(binding) {
            viewModel = bookViewModel
            rvBook.adapter = recordAdapter
            rvBookSummary.adapter = summaryAdapter

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvBookSummary.layoutManager = layoutManager
            rvBookSummary.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val position = layoutManager.findFirstVisibleItemPosition()
                }
            })
        }

        bookViewModel.errorMsg.observe(this@BookFragment, Observer { t ->
            showToast(t)
        })
    }
}