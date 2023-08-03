package com.rrm.rvkmr.ui

import com.rrm.rvkmr.data.models.UserModel
import com.rrm.rvkmr.retrofit.NetworkResponse

data class UserDetailsUiState(
    var networkState: NetworkResponse<UserModel>? = null,
)