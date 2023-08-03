package com.rrm.rvkmr.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
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
import com.rrm.rvkmr.data.models.UserModel
import com.rrm.rvkmr.retrofit.NetworkResponse
import com.rrm.rvkmr.ui.components.ErrorScreen
import com.rrm.rvkmr.ui.components.LoadingScreen
import com.rrm.rvkmr.ui.components.userDetails.UserDetailsBodyItem
import com.rrm.rvkmr.ui.components.userDetails.UserDetailsHeader
import com.rrm.rvkmr.ui.components.userDetails.UserDetailsSubHeader
import com.rrm.rvkmr.viewModel.UserDetailsViewModel

@Composable
fun UserDetailsScreen(
    userId: String?, onBackPressed: () -> Unit
) {

    val viewModel: UserDetailsViewModel = hiltViewModel()


    val uiState by viewModel.userDetailsUiState

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUsers(userLogin = userId ?: "")
    })

    UserDetailsUi(userId, uiState.networkState, onBackPressed)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsUi(
    userId: String?,
    uiState: NetworkResponse<UserModel>?,
    onBackPressed: () -> Unit
) {

    Scaffold(topBar = {
        Surface(shadowElevation = 16.dp) {
            TopAppBar(title = { Text(text = userId ?: "") }, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Navigation Button"
                    )
                }
            })
        }
    }) {

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
                        .padding(it)
                        .padding(16.dp)
                ) {

                    UserDetailsHeader(user = user)

                    UserDetailsSubHeader(  user = user )

                    Spacer(modifier = Modifier.height(16.dp))

                    UserDetailsBodyItem(
                        heading = "Email",
                        details = user?.email,
                        icon = Icons.Default.Email
                    )

                    UserDetailsBodyItem(
                        heading = "Location",
                        details = user?.location,
                        icon = Icons.Default.LocationOn
                    )


                }

            }

            else -> {}
        }

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
        {})

}

@Preview(showSystemUi = true)
@Composable
fun UserDetailsLoadingPreview() {
    UserDetailsUi(
        "User",
        NetworkResponse.Loading(),
        {})
}

@Preview(showSystemUi = true)
@Composable
fun UserDetailsErrorPreview() {
    UserDetailsUi(
        "User",
        NetworkResponse.Error("No User Found"),
        {})
}