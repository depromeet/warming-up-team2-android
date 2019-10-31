package com.depromeet.android.childcare.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityLoginBinding
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val kakaoSessionCallback: ISessionCallback by currentScope.inject()
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Session.getCurrentSession().addCallback(kakaoSessionCallback)
        initView()
    }

    private fun initView() {
        binding.viewModel = loginViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(kakaoSessionCallback)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}
