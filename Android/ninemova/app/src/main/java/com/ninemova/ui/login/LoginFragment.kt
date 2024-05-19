package com.ninemova.ui.login

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentLoginBinding
import com.ninemova.ui.MainActivity
import com.ninemova.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun initView() {
        with(binding) {
            btnSignin.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_signup)
            }
            btnLogin.setOnClickListener {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}
