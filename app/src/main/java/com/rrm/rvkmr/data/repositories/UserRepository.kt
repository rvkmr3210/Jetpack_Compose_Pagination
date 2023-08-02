package com.rrm.rvkmr.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rrm.rvkmr.retrofit.ApiEndPoint
import com.rrm.rvkmr.retrofit.ApiService
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
}