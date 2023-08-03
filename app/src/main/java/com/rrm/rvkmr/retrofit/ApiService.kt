package com.rrm.rvkmr.retrofit

import com.rrm.rvkmr.data.models.UserModel
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
}