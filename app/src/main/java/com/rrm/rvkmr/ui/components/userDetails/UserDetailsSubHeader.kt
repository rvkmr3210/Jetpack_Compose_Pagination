package com.rrm.rvkmr.ui.components.userDetails

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
import com.rrm.rvkmr.data.models.UserModel


@Composable
fun UserDetailsSubHeader(user: UserModel?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        UserDetailsSubHeaderItem(
            heading = "Public Repos",
            details = user?.publicRepos.toString()
        )

        Divider(
            Modifier
                .width(2.dp)
                .fillMaxHeight()
                .padding(vertical = 2.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        )

        UserDetailsSubHeaderItem(
            heading = "Public Gists",
            details = (user?.publicGists ?: 0).toString()
        )

        Divider(
            Modifier
                .width(2.dp)
                .fillMaxHeight()
                .padding(vertical = 2.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        )

        UserDetailsSubHeaderItem(
            heading = "Followers",
            details = user?.followers.toString()
        )

        Divider(
            Modifier
                .width(2.dp)
                .fillMaxHeight()
                .padding(vertical = 2.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        )

        UserDetailsSubHeaderItem(
            heading = "Following",
            details = user?.following.toString()
        )

    }
}

@Preview
@Composable
fun UserDetailsSubHeaderPreview() {
    UserDetailsSubHeader(user = UserModel(following = 0, followers = 0, publicRepos = 0))
}