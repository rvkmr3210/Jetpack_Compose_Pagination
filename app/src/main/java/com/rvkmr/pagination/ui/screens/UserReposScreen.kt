package com.rvkmr.pagination.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rvkmr.pagination.data.models.UserRepoModel
import com.rvkmr.pagination.retrofit.NetworkResponse
import com.rvkmr.pagination.ui.components.ErrorScreen
import com.rvkmr.pagination.ui.components.LoadingScreen
import com.rvkmr.pagination.ui.components.userRepos.UserReposListItem
import com.rvkmr.pagination.viewModel.UserReposViewModel

@Composable
fun UserReposScreen(userLogin: String?, onBackPressed: () -> Unit) {
    val viewModel: UserReposViewModel = hiltViewModel()

    val uiState by viewModel.userReposUiState


    LaunchedEffect(key1 = Unit) {
        viewModel.getUserRepos(userLogin = userLogin ?: "")
    }

    UserReposScreenUi(userLogin,uiState.networkState, onBackPressed)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserReposScreenUi(
    userLogin: String?,
    uiState: NetworkResponse<List<UserRepoModel>>?,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(title = { Text(text = "$userLogin Repos") }, navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Navigation Button"
                        )
                    }
                })
            }

        }
    ) {
        when (uiState) {
            is NetworkResponse.Loading -> {
                LoadingScreen()
            }

            is NetworkResponse.Success -> {
                val repos = uiState.data
                if (repos != null) {
                    LazyColumn(modifier = Modifier.padding(it)) {
                        items(items = repos) { repo ->
                            UserReposListItem(repo)
                        }
                    }
                } else {
                    ErrorScreen(message = "No repos found")
                }

            }

            else -> {
                ErrorScreen(message = uiState?.message ?: "No repos found")
            }
        }
    }
}


