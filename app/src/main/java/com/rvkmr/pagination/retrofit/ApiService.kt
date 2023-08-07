package com.rvkmr.pagination.retrofit

import com.rvkmr.pagination.data.models.UserModel
import com.rvkmr.pagination.data.models.UserRepoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ApiEndPoint.GET_USERS_LIST)
    suspend fun getUsersList(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): ArrayList<UserModel>

    @GET(ApiEndPoint.GET_USER)
    suspend fun getUser(@Path("user_login") userLogin: String): UserModel

    @GET(ApiEndPoint.GET_USER_REPOS)
    suspend fun getUserRepos(@Path("user_login") userLogin: String): List<UserRepoModel>
}