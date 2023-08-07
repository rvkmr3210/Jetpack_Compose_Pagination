package com.rvkmr.pagination.ui

import com.rvkmr.pagination.data.models.UserModel
import com.rvkmr.pagination.data.models.UserRepoModel
import com.rvkmr.pagination.retrofit.NetworkResponse

data class UserDetailsUiState(
    var networkState: NetworkResponse<UserModel>? = null,
)


data class UserReposUiState(
    var networkState: NetworkResponse<List<UserRepoModel>>? = null,
)