package com.depromeet.android.childcare.editbook

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityEditBookBinding
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

        editBookViewModel.openDatePickerEvent.observe(this@EditBookActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                showDatePickerDialog(it)
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

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, EditBookActivity::class.java)
        }
    }
}
