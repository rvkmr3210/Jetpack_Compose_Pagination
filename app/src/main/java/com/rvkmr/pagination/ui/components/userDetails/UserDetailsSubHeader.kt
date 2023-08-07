package com.rvkmr.pagination.ui.components.userDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rvkmr.pagination.data.models.UserModel


@Composable
fun UserDetailsSubHeader(
    user: UserModel?,
    onReposItemClick: () -> Unit,
    onGistsItemClick: () -> Unit,
    onFollowersItemClick: () -> Unit,
    onFollowingItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        UserDetailsSubHeaderItem(
            heading = "Public Repos",
            details = user?.publicRepos.toString(),
            onReposItemClick
        )

        Divider(
            Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = 2.dp),
            color = Color.Black
        )

        UserDetailsSubHeaderItem(
            heading = "Public Gists",
            details = (user?.publicGists ?: 0).toString(),
            onGistsItemClick
        )

        Divider(
            Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = 4.dp),
            color = Color.Black
        )

        UserDetailsSubHeaderItem(
            heading = "Followers",
            details = user?.followers.toString(),
            onFollowersItemClick
        )

        Divider(
            Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = 2.dp),
            color = Color.Black
        )

        UserDetailsSubHeaderItem(
            heading = "Following",
            details = user?.following.toString(),
            onFollowingItemClick
        )

    }
}

@Preview
@Composable
fun UserDetailsSubHeaderPreview() {
    UserDetailsSubHeader(user = UserModel(following = 0, followers = 0, publicRepos = 0),
        {},
        {},
        {},
        {})
}