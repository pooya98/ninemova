package com.ninemova.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ninemova.databinding.ActivityLoginBinding
import com.ninemova.R
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
