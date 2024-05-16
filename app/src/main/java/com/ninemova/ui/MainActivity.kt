package com.ninemova.ui

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ninemova.R
import com.ninemova.databinding.ActivityMainBinding
import com.ninemova.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navHostFragment: NavHostFragment

    override fun init() {
        initNavigation()
    }

    private fun initNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        with(binding) {
            bottomNavigationMain.apply {
                setupWithNavController(navHostFragment.navController)
            }
        }
    }
}
