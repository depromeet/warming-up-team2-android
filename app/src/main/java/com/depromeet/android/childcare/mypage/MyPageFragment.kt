package com.depromeet.android.childcare.mypage

import android.os.Bundle
import android.view.View
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.base.BaseFragment
import com.depromeet.android.childcare.databinding.FragmentMyPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageFragment: BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageViewModel: MyPageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = myPageViewModel
    }

    companion object {
        @JvmStatic
        fun newInstance(): MyPageFragment = MyPageFragment()
    }
}
