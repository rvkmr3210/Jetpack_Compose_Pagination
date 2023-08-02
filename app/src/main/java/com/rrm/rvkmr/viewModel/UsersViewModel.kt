package com.rrm.rvkmr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rrm.rvkmr.data.models.UserModel
import com.rrm.rvkmr.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(val repository: UserRepository) : ViewModel() {

    fun getUserList(): Flow<PagingData<UserModel>> =
        repository.getUsers().cachedIn(viewModelScope)

}