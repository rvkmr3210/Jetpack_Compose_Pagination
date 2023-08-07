package com.rvkmr.pagination.data.repositories

import com.rvkmr.pagination.retrofit.ApiService
import com.rvkmr.pagination.retrofit.NetworkResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(val apiService: ApiService) {
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


    fun getUserRepos(userLogin: String) = flow {
        emit(NetworkResponse.Loading())
        try {
            val userRepos = apiService.getUserRepos(userLogin)
            if (!userRepos.isNullOrEmpty()) {
                emit(NetworkResponse.Success(userRepos))
            } else {
                emit(NetworkResponse.Error("User repos Not Found"))
            }
        } catch (e: Exception) {
            emit(e.localizedMessage?.let { NetworkResponse.Error(it) })
        }
    }
}