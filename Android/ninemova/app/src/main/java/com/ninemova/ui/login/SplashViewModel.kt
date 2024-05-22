package com.ninemova.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninemova.Network.utils.RepositoryUtils
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _uiEvent = MutableSharedFlow<LoginViewEvent>()
    val uiEvent: SharedFlow<LoginViewEvent> = _uiEvent
    private val localDataStoreRepository = RepositoryUtils.localDataStoreRepository

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            val userId = localDataStoreRepository.getUserId()
            if (userId != -1) {
                _uiEvent.emit(LoginViewEvent.NavigateToMain)
            }
        }
    }
}
