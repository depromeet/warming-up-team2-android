package com.depromeet.android.childcare.addbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityAddItemSecondBinding
import com.depromeet.android.childcare.util.PermissionUtil.getPermission
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddBookSecondActivity :
    BaseActivity<ActivityAddItemSecondBinding>(R.layout.activity_add_item_second) {
    private val addItemViewModel: AddItemViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        getPermission(this, failed = { finish() })

        with(binding) {
            viewModel = addItemViewModel
            val categories = arrayOf(btnAddCategory1, btnAddCategory1, btnAddCategory3, btnAddCategory4)
            var selectedIdx = -1
            categories.forEachIndexed { index, btn ->
                btn.setOnClickListener {
                    btn.setBackgroundResource(R.drawable.btn_bg_round_blue)
                    categories[selectedIdx].setBackgroundResource(R.drawable.btn_bg_round_gray)
                    addItemViewModel.setCategory(btn.text.toString())
                    selectedIdx = index
                }
            }
        }
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, AddBookSecondActivity::class.java)
    }
}