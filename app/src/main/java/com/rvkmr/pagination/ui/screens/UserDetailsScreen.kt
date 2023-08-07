package com.rvkmr.pagination.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rvkmr.pagination.data.models.UserModel
import com.rvkmr.pagination.retrofit.NetworkResponse
import com.rvkmr.pagination.ui.components.ErrorScreen
import com.rvkmr.pagination.ui.components.LoadingScreen
import com.rvkmr.pagination.ui.components.userDetails.UserDetailsBody
import com.rvkmr.pagination.ui.components.userDetails.UserDetailsBodyItem
import com.rvkmr.pagination.ui.components.userDetails.UserDetailsHeader
import com.rvkmr.pagination.viewModel.UserDetailsViewModel

@Composable
fun UserDetailsScreen(
    userId: String?,
    onBackPressed: () -> Unit,
    onReposClick: () -> Unit
) {

    val viewModel: UserDetailsViewModel = hiltViewModel()


    val uiState by viewModel.userDetailsUiState

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUsers(userLogin = userId ?: "")
    })

    UserDetailsUi(
        userId,
        uiState.networkState,
        onBackPressed,
        onReposClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsUi(
    userId: String?,
    uiState: NetworkResponse<UserModel>?,
    onBackPressed: () -> Unit,
    onReposClick: () -> Unit
) {


    when (uiState) {
        is NetworkResponse.Loading -> {
            LoadingScreen()
        }

        is NetworkResponse.Error -> {
            ErrorScreen(uiState.message ?: "")
        }

        is NetworkResponse.Success -> {
            val user = uiState.data

            Column(
                Modifier
                    .fillMaxSize()
            ) {

                UserDetailsHeader(
                    user = user,
                    onBackPressed=onBackPressed,
                    onReposItemClick = onReposClick,
                    onGistsItemClick = {},
                    onFollowersItemClick = {},
                    onFollowingItemClick = {})

                Spacer(modifier = Modifier.height(24.dp))

                UserDetailsBody(user)

            }

        }

        else -> {}
    }

}


@Preview(showSystemUi = true)
@Composable
fun UserDetailsSuccessPreview() {
    UserDetailsUi(
        "User",
        NetworkResponse.Success(
            UserModel(
                email = "rvkmr3210@gmail.com",
                location = "Hyderabad, India",
                name = "Ravi Kumar Badini",
                avatarUrl = "https://avatars.githubusercontent.com/u/1825798?v=4",
                followers = 0,
                following = 0,
                publicRepos = 4
            )
        ),
        {}, {})

}

@Preview(showSystemUi = true)
@Composable
fun UserDetailsLoadingPreview() {
    UserDetailsUi(
        "User",
        NetworkResponse.Loading(),
        {}, {})
}

@Preview(showSystemUi = true)
@Composable
fun UserDetailsErrorPreview() {
    UserDetailsUi(
        "User",
        NetworkResponse.Error("No User Found"),
        {}, {})
}