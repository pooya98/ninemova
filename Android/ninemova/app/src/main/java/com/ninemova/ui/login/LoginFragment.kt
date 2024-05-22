package com.ninemova.ui.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ninemova.R
import com.ninemova.databinding.FragmentLoginBinding
import com.ninemova.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()
    override fun initView() {
        with(binding) {
            binding.viewModel = loginViewModel
            btnSignin.setOnClickListener {
                findNavController().navigate(R.id.action_login_to_signup)
            }
        }
    }
}
