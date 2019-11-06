package com.depromeet.android.childcare.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivityLoginBinding
import com.depromeet.android.childcare.main.MainActivity
import com.kakao.auth.Session
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObserve()
        Session.getCurrentSession().addCallback(loginViewModel)
    }

    private fun initView() {
        binding.apply {
            viewModel = loginViewModel
            lifecycleOwner = this@LoginActivity
        }
    }

    private fun initObserve() {
        loginViewModel.openMainEvent.observe(this@LoginActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    goMainActivity()
                }
            }
        })
    }

    private fun goMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(loginViewModel)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
