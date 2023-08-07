package com.rvkmr.pagination.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvkmr.pagination.data.repositories.UserRepository
import com.rvkmr.pagination.ui.UserReposUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(val repository: UserRepository) : ViewModel() {

     private var _userRepoUiState = mutableStateOf(UserReposUiState())

    val userReposUiState:State<UserReposUiState>
        get() = _userRepoUiState

    fun getUserRepos(userLogin:String) {
        viewModelScope.launch {
            repository.getUserRepos(userLogin).onEach {
                _userRepoUiState.value = _userRepoUiState.value.copy(networkState = it)
            }.launchIn(viewModelScope)
        }
    }

}