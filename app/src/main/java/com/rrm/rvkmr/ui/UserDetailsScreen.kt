package com.rrm.rvkmr.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rrm.rvkmr.data.models.UserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(userModel: UserModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "User") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Navigation Button"
                        )
                    }
                })
        }
    ) {
        Column(Modifier.padding(it)) {

        }
    }
}