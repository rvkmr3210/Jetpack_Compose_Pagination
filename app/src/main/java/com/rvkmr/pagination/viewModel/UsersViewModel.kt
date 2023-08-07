package com.rvkmr.pagination.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rvkmr.pagination.data.models.UserModel
import com.rvkmr.pagination.data.repositories.UserRepository
import com.rvkmr.pagination.data.repositories.UsersPagingSource
import com.rvkmr.pagination.retrofit.ApiEndPoint
import com.rvkmr.pagination.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val usersList = Pager(
        config = PagingConfig(
            pageSize = ApiEndPoint.PER_PAGE_LIMIT,
            enablePlaceholders = true,
        ),
        initialKey = 1,
        pagingSourceFactory = {
            UsersPagingSource(apiService)
        }).flow.cachedIn(viewModelScope)

}