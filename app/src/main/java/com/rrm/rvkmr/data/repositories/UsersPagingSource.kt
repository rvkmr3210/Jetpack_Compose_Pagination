package com.rrm.rvkmr.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rrm.rvkmr.data.models.UserModel
import com.rrm.rvkmr.retrofit.ApiEndPoint
import com.rrm.rvkmr.retrofit.ApiService
import javax.inject.Inject

class UsersPagingSource @Inject constructor(val apiService: ApiService) :
    PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ApiEndPoint.PER_PAGE_LIMIT)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ApiEndPoint.PER_PAGE_LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            val since = params.key ?: 1
            val response =
                apiService.getUsersList(
                    since = since,
                    perPage = params.loadSize/*ApiEndPoint.PER_PAGE_LIMIT*/
                )
            LoadResult.Page(
                data = response,
                prevKey = if (since == 1) null else since.minus(ApiEndPoint.PER_PAGE_LIMIT),
                nextKey = if (response.isEmpty()) null else since.plus(ApiEndPoint.PER_PAGE_LIMIT),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}