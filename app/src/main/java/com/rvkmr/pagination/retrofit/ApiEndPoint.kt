package com.rvkmr.pagination.retrofit

class ApiEndPoint {
    companion object {
        const val PER_PAGE_LIMIT = 20
        const val BASE_URL = "https://api.github.com"
        const val GET_USERS_LIST = "/users"
        const val GET_USER="/users/{user_login}"
        const val GET_USER_REPOS="/users/{user_login}/repos"
    }
}