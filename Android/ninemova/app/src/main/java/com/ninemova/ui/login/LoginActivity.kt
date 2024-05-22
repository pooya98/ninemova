package com.ninemova.ui.login

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import com.ninemova.Network.repositoryimpl.LocalDataStoreRepositoryImpl
import com.ninemova.Network.utils.RepositoryUtils
import com.ninemova.R
import com.ninemova.core.dataStore
import com.ninemova.databinding.ActivityLoginBinding
import com.ninemova.ui.MainActivity
import com.ninemova.ui.base.BaseActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var splashScreen: SplashScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        RepositoryUtils.localDataStoreRepository =
            LocalDataStoreRepositoryImpl(applicationContext.dataStore)
        splashScreen = installSplashScreen()
        startAnimation()
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        collectUiEvent()
        initNavigation()
    }

    private fun initNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_login) as NavHostFragment
    }

    private fun collectUiEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is LoginViewEvent.NavigateToMain -> {
                            startActivity(this@LoginActivity, MainActivity::class.java)
                            finish()
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun startAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.view.height.toFloat(),
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 1000
            slideUp.doOnEnd { splashScreenView.remove() }
            slideUp.start()
        }
    }
}
