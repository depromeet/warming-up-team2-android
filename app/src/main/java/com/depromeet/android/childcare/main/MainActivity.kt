package com.depromeet.android.childcare.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.depromeet.android.childcare.ADD_ITEM_SUCCESS
import com.depromeet.android.childcare.EDIT_ITEM
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.addbook.AddBookActivity
import com.depromeet.android.childcare.databinding.ActivityMainBinding
import com.depromeet.android.childcare.mypage.MyPageFragment
import com.studyfirstproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
    private var myPageFragment: MyPageFragment? = null

    val changeRecordsEventBus: ChangeRecordsEventBus by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.viewpagerMain.apply {
            adapter = viewPagerAdapter
            offscreenPageLimit = binding.tabMain.tabCount
        }
        binding.tabMain.apply {
            setupWithViewPager(binding.viewpagerMain)
        }
        binding.ivMypage.setOnClickListener {
            fl_mypage_content.visibility = View.VISIBLE
            myPageFragment = MyPageFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_mypage_content, myPageFragment!!)
                .commit()
        }
        binding.fab.setOnClickListener {
            startActivityForResult(AddBookActivity.getStartIntent(this), ADD_ITEM_SUCCESS)
        }
    }

    override fun onBackPressed() {
        if (fl_mypage_content.visibility == View.VISIBLE) {
            fl_mypage_content.visibility = View.GONE
            myPageFragment?.let {
                supportFragmentManager
                    .beginTransaction()
                    .remove(it)
                    .commit()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_ITEM && resultCode == Activity.RESULT_OK) {
            changeRecordsEventBus.triggerEvent()
        }
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
