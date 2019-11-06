package com.depromeet.android.childcare.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityMainBinding
import com.depromeet.android.childcare.mypage.MyPageFragment
import com.studyfirstproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)

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
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_mypage_content, MyPageFragment.newInstance())
                .commit()
        }
    }

    override fun onBackPressed() {
        if (fl_mypage_content.visibility == View.VISIBLE) {
            fl_mypage_content.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
