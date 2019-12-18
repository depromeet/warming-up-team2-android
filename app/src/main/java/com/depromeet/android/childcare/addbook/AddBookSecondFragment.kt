package com.depromeet.android.childcare.addbook

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.PICK_FROM_ALBUM
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentAddBookSecondBinding
import com.depromeet.android.childcare.util.PermissionUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AddBookSecondFragment :
    BaseFragment<FragmentAddBookSecondBinding>(R.layout.fragment_add_book_second) {

    private val addItemViewModel: AddItemViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initObserve()
    }

    private fun initView() {
        with(binding) {
            viewModel = addItemViewModel
        }
    }

    private fun initObserve() {
        activity?.let {baseActivity ->
            addItemViewModel.openGalleryEvent.observe(this, Observer { event ->
                event.getContentIfNotHandled()?.let { unit ->
                    // 퍼미션 체크
                    PermissionUtil.getPermission(baseActivity,
                        success = { pickImg() },
                        failed = { Toast.makeText(baseActivity, "권한을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show() }
                    )
                }
            })
        }
    }

    private fun pickImg() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FROM_ALBUM && resultCode == AppCompatActivity.RESULT_OK) {
            data?.let {
                addItemViewModel.changeImgUrl(it.data)
            }
        }
    }
}