package com.rrm.rvkmr.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rrm.rvkmr.data.models.UserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListItem(user: UserModel) {
    ListItem(
        modifier = Modifier.clickable {
//            navController.navigate(Screen.UserDetails.passUserDetails(user.id))
        },
        headlineText = {
            Text(
                text = user.login ?: "",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        },
        leadingContent = {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(60.dp)
                    .height(60.dp),
                model = user.avatarUrl,
                contentDescription = "User Profile Picture",
                contentScale = ContentScale.Fit,
            )
        },

        )

}