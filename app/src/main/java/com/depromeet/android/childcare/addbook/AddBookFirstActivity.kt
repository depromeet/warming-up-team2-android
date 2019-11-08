package com.depromeet.android.childcare.addbook

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.android.childcare.EXTRA_RECORD_AMOUNT
import com.depromeet.android.childcare.EXTRA_RECORD_DATE
import com.depromeet.android.childcare.EXTRA_RECORD_TITLE
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityAddItemFirstBinding
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class AddBookFirstActivity : BaseActivity<ActivityAddItemFirstBinding>(R.layout.activity_add_item_first) {
    private val addItemViewModel: AddItemViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val context = this

        with(binding) {
            viewModel = addItemViewModel

            etAddDate.setOnClickListener {
                showDatePickerDialog()
            }

            btnAddNext.setOnClickListener {
                val where = etAddWhere.text.toString()
                val amount = etAddAmount.text.toString()

                if (where.isNotEmpty() && amount.isNotEmpty()) {
                    val startIntent = AddBookSecondActivity.getStartIntent(context)
                    startIntent.putExtra(EXTRA_RECORD_TITLE, where)
                    startIntent.putExtra(EXTRA_RECORD_AMOUNT, amount.toInt())
                    startIntent.putExtra(EXTRA_RECORD_DATE, addItemViewModel.date.value)
                    startActivity(startIntent)
                }
            }
        }
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, AddBookFirstActivity::class.java)
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { _, y, m, d ->
                addItemViewModel.changeDate(y, m + 1, d)
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)
        ).show()
    }
}