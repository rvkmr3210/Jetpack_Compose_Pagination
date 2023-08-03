package com.rrm.rvkmr.ui.components.userDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rrm.rvkmr.data.models.UserModel


@Composable
fun UserDetailsSubHeader(user: UserModel?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        UserDetailsSubHeaderItem(
            heading = "Public Repos",
            details = user?.publicRepos.toString()
        )
        UserDetailsSubHeaderItem(
            heading = "Followers",
            details = user?.followers.toString()
        )
        UserDetailsSubHeaderItem(
            heading = "Following",
            details = user?.following.toString()
        )

    }
}