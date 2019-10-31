package com.depromeet.android.childcare.splash

import android.animation.Animator
import android.os.Bundle
import androidx.lifecycle.Observer
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.databinding.ActivitySplashBinding
import com.depromeet.android.childcare.login.LoginActivity
import com.depromeet.android.childcare.main.MainActivity
import com.studyfirstproject.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashViewModel: SplashViewModel by viewModel()

    companion object {
        const val NOT_OPEN = "NOT_OPEN"
        const val OPEN_LOGIN = "OPEN_LOGIN"
        const val OPEN_MAIN = "OPEN_MAIN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.openAppEvent.observe(this@SplashActivity, Observer { event ->
            event.getContentIfNotHandled()?.let {
                when(it) {
                    OPEN_MAIN -> goMainActivity()
                    OPEN_LOGIN -> goLoginActivity()
                }
            }
        })

        binding.lottieViewSplash.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                splashViewModel.onAnimationEnded()
            }

            override fun onAnimationEnd(animation: Animator?) {}

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {}

        })
    }

    private fun goLoginActivity() {
        LoginActivity.start(this)
        finish()
    }

    private fun goMainActivity() {
        MainActivity.start(this)
        finish()
    }
}
