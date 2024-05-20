package com.ninemova.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onTextChanged(inputType: String, s: CharSequence, start: Int, before: Int, count: Int) {
        _uiState.update { uiState ->
            val updatedState = when (inputType) {
                "id" -> uiState.copy(id = s.toString())
                "password" -> uiState.copy(password = s.toString())
                else -> uiState
            }

            updatedState.copy(
                isValidated = (updatedState.id.isNullOrEmpty() || updatedState.password.isNullOrEmpty()).not()
            )
        }
    }
}