package com.depromeet.android.childcare.home

import androidx.lifecycle.ViewModelProviders
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    val viewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }
}