package com.depromeet.android.childcare.addbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.depromeet.android.childcare.PICK_FROM_ALBUM
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
            val categories = arrayOf(btnAddCategory1, btnAddCategory2, btnAddCategory3, btnAddCategory4)
            var selectedIdx = -1
            ivAddContent.setOnClickListener { pickImg() }
            categories.forEachIndexed { index, btn ->
                btn.setOnClickListener {
                    if (selectedIdx != -1) {
                        categories[selectedIdx].setBackgroundResource(R.drawable.btn_bg_round_gray)
                    }
                    selectedIdx = index
                    btn.setBackgroundResource(R.drawable.btn_bg_round_blue)
                    addItemViewModel.setCategory(btn.text.toString())
                }
            }
        }
    }

    private fun pickImg() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK) {
            binding.ivAddContent.setImageURI(data?.data)
            binding.ivAddContent.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, AddBookSecondActivity::class.java)
    }
}