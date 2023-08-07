package com.rvkmr.pagination.ui.components.userDetails

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rvkmr.pagination.data.models.UserModel

@Composable
fun UserDetailsBody(user: UserModel?) {

    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.padding(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                UserDetailsBodyItem(
                    heading = "Email",
                    details = user?.email ?: "N/A",
                    icon = Icons.Default.Email
                )
                Spacer(modifier = Modifier.height(12.dp))
                UserDetailsBodyItem(
                    heading = "Company",
                    details = user?.company ?: "N/A",
                    icon = Icons.Default.Home
                )

            }
        }
    }
}

@Preview
@Composable
fun UserDetailsBodyPreview() {
    UserDetailsBody(user = UserModel())
}