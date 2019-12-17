package com.depromeet.android.childcare.editbook

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.PICK_FROM_ALBUM
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityEditBookBinding
import com.depromeet.android.childcare.util.PermissionUtil
import com.depromeet.android.childcare.util.toDate
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class EditBookActivity : BaseActivity<ActivityEditBookBinding>(R.layout.activity_edit_book) {

    private val editBookViewModel: EditBookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            viewModel = editBookViewModel
            lifecycleOwner = this@EditBookActivity
        }

        initObserve()
    }

    private fun initObserve() {
        editBookViewModel.closeEditBookViewEvent.observe(this@EditBookActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    finish()
                }
            }
        })

        editBookViewModel.openDatePickerEvent.observe(this@EditBookActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                showDatePickerDialog(it)
            }
        })

        editBookViewModel.openGalleryEvent.observe(this@EditBookActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    // 퍼미션 체크
                    PermissionUtil.getPermission(this, failed = { finish() })

                    // 갤러리 호출
                    pickImg()
                }
            }
        })

        editBookViewModel.successEditBookEvent.observe(this@EditBookActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                Intent().apply {
                    // putExtra(EXTRA_BOOK_POSITION, it)
                    setResult(Activity.RESULT_OK, this)
                }
                finish()
            }
        })
    }

    private fun showDatePickerDialog(dateString: String) {
        val cal = Calendar.getInstance()
        dateString.toDate("yyyy-MM-dd")?.let {
            cal.time = it
        }
        DatePickerDialog(
            this,
            R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                editBookViewModel.changeDate(year, month + 1, day)
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)
        ).show()
    }

    private fun pickImg() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FROM_ALBUM && resultCode == RESULT_OK) {
            data?.let {
                editBookViewModel.changeImgUrl(it.data)
            }
        }
    }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, EditBookActivity::class.java)
        }
    }
}
