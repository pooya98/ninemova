package com.ninemova.ui.login

import androidx.navigation.fragment.NavHostFragment
import com.ninemova.R
import com.ninemova.databinding.ActivityLoginBinding
import com.ninemova.ui.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var navHostFragment: NavHostFragment

    override fun init() {
        initNavigation()
    }

    private fun initNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_login) as NavHostFragment
    }
}
