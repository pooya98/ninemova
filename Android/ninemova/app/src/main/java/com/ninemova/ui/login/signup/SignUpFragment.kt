package com.ninemova.ui.login.signup

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ninemova.R
import com.ninemova.databinding.FragmentSignUpBinding
import com.ninemova.ui.MainActivity
import com.ninemova.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val signUpViewModel: SignUpViewModel by viewModels()
    override fun initView() {
        with(binding) {
            viewModel = signUpViewModel
            btnSignup.setOnClickListener {
                signUpViewModel.signUp()
            }
        }
        collectUiEvent()
    }

    private fun collectUiEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.uiEvent.collectLatest { event ->
                    when (event) {
                        is SignUpViewEvent.Error -> {
                            showMessage(event.errorMessage)
                        }

                        is SignUpViewEvent.Success -> {
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }
                    }
                }
            }
        }
    }
}
