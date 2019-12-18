package com.depromeet.android.childcare.addbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.ADD_ITEM
import com.depromeet.android.childcare.ADD_ITEM_SUCCESS
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityAddBookBinding
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class AddBookActivity :
    BaseActivity<ActivityAddBookBinding>(R.layout.activity_add_book) {
    private val addItemViewModel: AddItemViewModel by viewModel()
    private val addBookFirstFragment: AddBookFirstFragment by currentScope.inject()
    private val addBookSecondFragment: AddBookSecondFragment by currentScope.inject()
    private val addBookBackStack: Stack<Fragment> = Stack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObserve()
    }

    private fun initView() {

        with(binding) {
            viewModel = addItemViewModel
            lifecycleOwner = this@AddBookActivity
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_add_book_content, addBookFirstFragment)
            .commit()
    }

    private fun initObserve() {
        addItemViewModel.openAddBookSecondEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                addSecondFragment()
            }
        })

        addItemViewModel.goBackAddBookEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                onBackPressed()
            }
        })
    }

    private fun addSecondFragment() {
        addBookBackStack.push(addBookSecondFragment)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_add_book_content, addBookSecondFragment)
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ITEM && resultCode == ADD_ITEM_SUCCESS) {
            setResult(ADD_ITEM_SUCCESS)
            finish()
        }
    }

    override fun onBackPressed() {
        if (addBookBackStack.empty()) {
            super.onBackPressed()
        } else {
            addBookBackStack.pop()
            supportFragmentManager
                .beginTransaction()
                .remove(addBookSecondFragment)
                .commit()
        }
    }

    companion object {

        fun getStartIntent(context: Context) = Intent(context, AddBookActivity::class.java)
    }
}