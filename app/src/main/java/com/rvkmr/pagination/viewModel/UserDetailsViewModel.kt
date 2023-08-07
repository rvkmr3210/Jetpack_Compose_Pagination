package com.rvkmr.pagination.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvkmr.pagination.data.repositories.UserRepository
import com.rvkmr.pagination.ui.UserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(val repository: UserRepository) : ViewModel() {

    private var _userDetailsUiState = mutableStateOf(UserDetailsUiState())

    val userDetailsUiState: State<UserDetailsUiState>
        get() = _userDetailsUiState

    fun getUsers(userLogin: String) {
        viewModelScope.launch {
            repository.getUser(userLogin).onEach {
                _userDetailsUiState.value = _userDetailsUiState.value.copy(networkState = it)
            }.launchIn(viewModelScope)
        }
    }

}