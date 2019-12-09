package com.depromeet.android.childcare.editbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityEditBookBinding
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditBookActivity : BaseActivity<ActivityEditBookBinding>(R.layout.activity_edit_book) {

    private val editBookViewModel: EditBookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            viewModel = editBookViewModel
            lifecycleOwner = this@EditBookActivity
        }

        initObserve();
    }

    private fun initObserve() {
        editBookViewModel.closeEditBookViewEvent.observe(this@EditBookActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    finish()
                }
            }
        })
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, EditBookActivity::class.java)
        }
    }
}
