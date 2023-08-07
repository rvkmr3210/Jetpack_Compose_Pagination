package com.rvkmr.pagination.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.rvkmr.pagination.R
import com.rvkmr.pagination.data.models.UserModel
import com.rvkmr.pagination.extensions.toJson
import com.rvkmr.pagination.ui.navigation.NavScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListItem(navController: NavController, user: UserModel) {

    val customCardColors = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        disabledContentColor = MaterialTheme.colorScheme.surface,
        disabledContainerColor = MaterialTheme.colorScheme.onSurface,
    )
    val customCardElevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp,
        pressedElevation = 2.dp,
        focusedElevation = 4.dp
    )
    Box(modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = customCardColors,
            elevation = customCardElevation
        ) {
            ListItem(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(NavScreen.UserDetails.passUserDetails(user.login))
                    },
                headlineText = {
                    Text(
                        text = user.login ?: "",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                leadingContent = {
                    AsyncImage(
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(90.dp)
                            .height(90.dp),
                        model = user.avatarUrl,
                        contentDescription = "User Profile Picture",
                        contentScale = ContentScale.Fit,
                    )
                },
                trailingContent = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavScreen.UserRepos.passUserDetails(userLogin = user.login))
                        },
                        content = {
//                            BadgedBox(badge = {
//                                Badge(
//                                    containerColor = MaterialTheme.colorScheme.primary,
//                                    contentColor = MaterialTheme.colorScheme.onPrimary
//                                ) { Text("8") }
//                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_reositories),
                                contentDescription = "User repos button"
                            )
//                            }

                        }
                    )
                }
            )
        }
    }

}

@Preview
@Composable
fun UserListItemPreview() {
    val navController = rememberNavController()
    UserListItem(navController, UserModel(login = "rvkmr3210"))
}