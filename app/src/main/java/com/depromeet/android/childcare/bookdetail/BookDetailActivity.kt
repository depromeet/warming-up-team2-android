package com.depromeet.android.childcare.bookdetail

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.depromeet.android.childcare.EXTRA_BOOK_POSITION
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityBookDetailBinding
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class BookDetailActivity : BaseActivity<ActivityBookDetailBinding>(R.layout.activity_book_detail) {

    private val displayMetrics = DisplayMetrics()
    private var position = 0
    private val snapHelper: PagerSnapHelper = PagerSnapHelper()
    private val navigator = BookDetailNavigatorImpl(this)

    private val bookDetailViewModel: BookDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = intent.getIntExtra(EXTRA_BOOK_POSITION, 0)
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        initView()
    }

    private fun initView() {
        binding.apply {
            viewModel = bookDetailViewModel
            rvBookDetail.adapter = BookDetailListAdapter(navigator, displayMetrics.widthPixels)
            rvBookDetail.addItemDecoration(BookDetailListItemDecoration())
            snapHelper.attachToRecyclerView(rvBookDetail)

            val offset = ((displayMetrics.widthPixels * 0.12) -
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4.5f, displayMetrics)).toInt()
            (rvBookDetail.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, offset)

            btnBookDetailBack.setOnClickListener {
                this@BookDetailActivity.isFinishing
            }
        }
    }
}
