package com.depromeet.android.childcare.editbook

import android.content.Context
import android.content.Intent
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityEditBookBinding
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditBookActivity : BaseActivity<ActivityEditBookBinding>(R.layout.activity_edit_book) {

    private val editBookViewModel: EditBookViewModel by viewModel()


    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, EditBookActivity::class.java)
        }
    }
}
