@file:OptIn(ExperimentalMaterial3Api::class)

package com.rrm.rvkmr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rrm.rvkmr.ui.components.UserListItem
import com.rrm.rvkmr.ui.theme.ComposePaginationSampleTheme
import com.rrm.rvkmr.viewModel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePaginationSampleTheme {
                // A surface container using the 'background' color from the theme
                UsersList()
            }
        }
    }
}

@Composable
fun UsersList(usersViewModel: UsersViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Github Users List") },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        val users = usersViewModel.getUserList().collectAsLazyPagingItems()
        val listState: LazyListState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight(), listState
        ) {

            items(count = users.itemCount) { index: Int ->
                val user = users[index]
                UserListItem(user!!)
            }

            when (val state = users.loadState.prepend) { //FIRST LOAD
                is LoadState.Error -> {
                    error(state.error.message?:"")
                }

                is LoadState.Loading -> {
                    loading()
                }

                is LoadState.NotLoading -> Unit
            }


            when (val state = users.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {
                    error(state.error.message?:"")
                }

                is LoadState.Loading -> {
                    loading()
                }

                is LoadState.NotLoading -> Unit
            }


            when (val state = users.loadState.append) { // Pagination
                is LoadState.Error -> {
                    error(state.error.message?:"")
                }

                is LoadState.Loading -> {
                    loading()
                }

                is LoadState.NotLoading -> Unit

            }
        }


    }

}

private fun LazyListScope.loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

private fun LazyListScope.error(
    message: String
) {
    item {
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePaginationSampleTheme {
        UsersList()
    }
}