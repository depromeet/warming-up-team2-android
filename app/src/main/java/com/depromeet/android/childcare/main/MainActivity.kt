package com.depromeet.android.childcare.main

import android.os.Bundle
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityMainBinding
import com.studyfirstproject.base.BaseActivity

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
    }
}
