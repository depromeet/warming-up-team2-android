package com.depromeet.android.childcare.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.depromeet.android.childcare.book.BookFragment
import com.depromeet.android.childcare.home.HomeFragment

class MainViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val pages = arrayOf(HomeFragment(), BookFragment())
    private val pageTitles = arrayOf("홈", "가계부")

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? = pageTitles[position]
}