package com.depromeet.android.childcare.addbook

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentAddBookFirstBinding
import com.depromeet.android.childcare.util.toDate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*


class AddBookFirstFragment : BaseFragment<FragmentAddBookFirstBinding>(R.layout.fragment_add_book_first) {

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
        addItemViewModel.openDatePickerEvent.observe(this, Observer {event ->
            event.getContentIfNotHandled()?.let {
                showDatePickerDialog(it)
            }
        })
    }

    private fun showDatePickerDialog(dateString: String) {
        activity?.let {
            val cal = Calendar.getInstance()
            dateString.toDate("yyyy-MM-dd")?.let {date ->
                cal.time = date
            }
            DatePickerDialog(
                it,
                R.style.DialogTheme,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    addItemViewModel.changeDate(year, month + 1, day)
                },
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)
            ).show()
        }
    }
}