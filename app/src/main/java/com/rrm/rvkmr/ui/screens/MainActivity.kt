@file:OptIn(ExperimentalMaterial3Api::class)

package com.rrm.rvkmr.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rrm.rvkmr.ui.components.ErrorScreen
import com.rrm.rvkmr.ui.components.LoadingScreen
import com.rrm.rvkmr.ui.components.UserListItem
import com.rrm.rvkmr.ui.navigation.NavScreen
import com.rrm.rvkmr.ui.navigation.UserNavGraph
import com.rrm.rvkmr.ui.theme.ComposePaginationSampleTheme
import com.rrm.rvkmr.viewModel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        installSplashScreen()

        setContent {
            ComposePaginationSampleTheme {
                val navController = rememberNavController()
                UserNavGraph(navController, NavScreen.UsersList.route)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersList(navController: NavController, usersViewModel: UsersViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 8.dp) {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = { Text(text = "Github Users") },
                )
            }


        },
        modifier = Modifier.fillMaxSize(),
    ) {
        val users = usersViewModel.usersList.collectAsLazyPagingItems()
        val listState: LazyListState = rememberLazyListState()

        Log.d("Paging List State Append", "${users.loadState}")

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight(),
            state = listState,
            contentPadding = PaddingValues(20.dp)
        ) {

            items(count = users.itemCount) { index: Int ->
                val user = users[index]
                UserListItem(navController, user!!)
            }

            when (val state = users.loadState.prepend) { //FIRST LOAD

                is LoadState.Error -> {
                    error(state.error.message ?: "")
                    Log.d("PagingState - prepend - ", "Error")

                }

                is LoadState.Loading -> {
                    Log.d("PagingState - prepend - ", "Loading")

                    loading()
                }

                is LoadState.NotLoading -> {
                    Log.d("PagingState - prepend - ", "Not Loading")
                }
            }


            when (val state = users.loadState.refresh) {
                //FIRST LOAD
                is LoadState.Error -> {
                    Log.d("PagingState - refresh - ", "Error")

                    error(state.error.message ?: "")
                }

                is LoadState.Loading -> {
                    Log.d("PagingState - refresh - ", "Loading")

                    loading()
                }

                is LoadState.NotLoading -> {
                    Log.d("PagingState - refresh - ", "Not Loading")
                }
            }

            when (val state = users.loadState.append) { // Pagination
                is LoadState.Error -> {
                    Log.d("PagingState - append - ", "Loading")
                    error(state.error.message ?: "")
                }

                is LoadState.Loading -> {
                    Log.d("PagingState - append - ", "Loading")
                    loading()
                }

                is LoadState.NotLoading -> {
                    Log.d("PagingState - append - ", "Loading")
                }

            }
        }


    }

}

private fun LazyListScope.loading() {
    item {
        LoadingScreen()
    }
}

private fun LazyListScope.error(
    message: String
) {
    item {
        ErrorScreen(
             message= message,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePaginationSampleTheme {
        UsersList(rememberNavController())
    }
}