package com.ninemova.ui.login.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.RepositoryUtils
import com.ninemova.Network.request.SignUpRequest
import com.ninemova.ui.login.LoginViewEvent
import com.ninemova.ui.util.ErrorMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState
    private val _uiEvent = MutableSharedFlow<LoginViewEvent>()
    val uiEvent: SharedFlow<LoginViewEvent> = _uiEvent
    private val userRepository = RepositoryUtils.userRepository
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository

    fun onTextChanged(inputType: String, s: CharSequence, start: Int, before: Int, count: Int) {
        _uiState.update { uiState ->
            when (inputType) {
                "id" -> uiState.copy(id = s.toString())

                "password" -> uiState.copy(password = s.toString())

                "repassword" -> uiState.copy(rePassword = s.toString())

                "nickname" -> uiState.copy(nickName = s.toString())

                else -> uiState
            }
        }
        validation()
    }

    private fun validation() {
        val isValidation = with(uiState.value) {
            (
                    id.isNullOrEmpty() ||
                            password.isNullOrEmpty() ||
                            rePassword.isNullOrEmpty() ||
                            nickName.isNullOrEmpty() ||
                            password != rePassword
                    ).not()
        }
        _uiState.update { uiState ->
            uiState.copy(
                isValidated = isValidation,
            )
        }
    }

    fun signUp() {
        viewModelScope.launch {
            userRepository.signUp(
                SignUpRequest(
                    userName = uiState.value.id,
                    passWord = uiState.value.password,
                    nickName = uiState.value.nickName,
                ),
            ).collectLatest { user ->
                if (user != null) {
                    localDataStoreRepository.saveUser(user)
                    _uiEvent.emit(
                        LoginViewEvent.NavigateToMain
                    )
                } else {
                    _uiEvent.emit(
                        LoginViewEvent.Error(errorMessage = ErrorMessage.SIGN_UP_ERROR_MESSAGE),
                    )
                }
            }
        }
    }
}
