package com.depromeet.android.childcare.editbook

import android.content.Context
import android.content.Intent
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityEditBookBinding
import com.depromeet.android.childcare.main.MainActivity
import com.studyfirstproject.base.BaseActivity

class EditBookActivity : BaseActivity<ActivityEditBookBinding>(R.layout.activity_edit_book) {


    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
