package com.depromeet.android.childcare.addbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityAddItemFirstBinding
import com.studyfirstproject.base.BaseActivity


class AddBookFirstActivity : BaseActivity<ActivityAddItemFirstBinding>(R.layout.activity_add_item_first) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnAddNext.setOnClickListener {
            val startIntent = AddBookSecondActivity.getStartIntent(this)
            startActivity(startIntent)
        }
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, AddBookFirstActivity::class.java)
    }
}