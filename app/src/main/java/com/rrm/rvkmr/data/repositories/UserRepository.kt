package com.rrm.rvkmr.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rrm.rvkmr.data.models.UserModel
import com.rrm.rvkmr.retrofit.ApiEndPoint
import com.rrm.rvkmr.retrofit.ApiService
import com.rrm.rvkmr.retrofit.NetworkResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(val apiService: ApiService) {
    fun getUsers() = Pager(
        config = PagingConfig(
            pageSize = ApiEndPoint.PER_PAGE_LIMIT,
            enablePlaceholders = true,
        ),
        initialKey = 1,
        pagingSourceFactory = {
            UsersPagingSource(apiService)
        }).flow

    fun getUser(userLogin: String) = flow {
        emit(NetworkResponse.Loading())
        try {
            val user = apiService.getUser(userLogin)
            if (user.message.isNullOrEmpty()) {
                emit(NetworkResponse.Success(user))
            } else {
                emit(NetworkResponse.Error(user.message ?: "User Not Found"))
            }
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.localizedMessage))
        }
    }
}