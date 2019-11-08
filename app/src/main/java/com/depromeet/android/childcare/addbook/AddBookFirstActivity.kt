package com.depromeet.android.childcare.addbook

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.*
import com.depromeet.android.childcare.databinding.ActivityAddItemFirstBinding
import com.studyfirstproject.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_add_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class AddBookFirstActivity :
    BaseActivity<ActivityAddItemFirstBinding>(R.layout.activity_add_item_first) {
    private val addItemViewModel: AddItemViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val context = this

        with(binding) {
            viewModel = addItemViewModel
            toolbar.setOnClickListener { finish() }
            etAddDate.setOnClickListener { showDatePickerDialog() }
            btnAddNext.setOnClickListener {
                val where = etAddWhere.text.toString()
                val amount = etAddAmount.text.toString()

                if (where.isNotEmpty() && amount.isNotEmpty()) {
                    val startIntent = AddBookSecondActivity.getStartIntent(context)
                    startIntent.putExtra(EXTRA_RECORD_TITLE, where)
                    startIntent.putExtra(EXTRA_RECORD_AMOUNT, amount.toInt())
                    startActivityForResult(startIntent, ADD_ITEM)
                } else {
                    showToast(getString(R.string.add_item_msg_input))
                }
            }
            btnAddCard.setOnClickListener {
                addItemViewModel.setPaymentMethod("카드")
                btnAddCard.setBackgroundResource(R.drawable.btn_bg_round_blue)
                btnAddCash.setBackgroundResource(R.drawable.btn_bg_round_gray)
            }
            btnAddCash.setOnClickListener {
                addItemViewModel.setPaymentMethod("현금")
                btnAddCash.setBackgroundResource(R.drawable.btn_bg_round_blue)
                btnAddCard.setBackgroundResource(R.drawable.btn_bg_round_gray)
            }
            addItemViewModel.date.observe(this@AddBookFirstActivity, Observer { t ->
                etAddDate.setText(addItemViewModel.date.value)
            })
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ITEM && resultCode == ADD_ITEM_SUCCESS) {
            setResult(ADD_ITEM_SUCCESS)
            finish()
        }
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, AddBookFirstActivity::class.java)
    }
}