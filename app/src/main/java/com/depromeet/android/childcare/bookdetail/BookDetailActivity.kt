package com.depromeet.android.childcare.bookdetail

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityBookDetailBinding
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BookDetailActivity : BaseActivity<ActivityBookDetailBinding>(R.layout.activity_book_detail), BookDetailNavigator {

    private val bookDetailViewModel: BookDetailViewModel by viewModel { parametersOf(this) }
    private val snapHelper: PagerSnapHelper = PagerSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.apply {
            viewModel = bookDetailViewModel
            rvBookDetail.layoutManager = LinearLayoutManager(this@BookDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            snapHelper.attachToRecyclerView(rvBookDetail)
        }
    }

    override fun showOptionDialog(feedId: Int) {
        Toast.makeText(this, "option click in bookdetail with id $feedId", Toast.LENGTH_SHORT).show()
    }
}
