package com.ninemova.ui.login

import android.content.Intent
import com.ninemova.databinding.ActivityLoginBinding
import com.ninemova.R
import com.ninemova.ui.MainActivity
import com.ninemova.ui.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun init() {
      binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
            finish()
        }
    }
}
