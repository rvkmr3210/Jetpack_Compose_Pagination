package com.rvkmr.pagination.ui.components.userDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rvkmr.pagination.data.models.UserModel

@Composable
fun UserDetailsHeader(
    user: UserModel?,
    onBackPressed: () -> Unit,
    onReposItemClick: () -> Unit,
    onGistsItemClick: () -> Unit,
    onFollowersItemClick: () -> Unit,
    onFollowingItemClick: () -> Unit,
) {

    val customCardElevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp, pressedElevation = 2.dp, focusedElevation = 4.dp
    )

    val colorStops = arrayOf(
        0f to MaterialTheme.colorScheme.primary,
        1f to MaterialTheme.colorScheme.primaryContainer,
    )
    val brush = Brush.linearGradient(
        colorStops = colorStops,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.spacedBy((-24).dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Max)
                .background(brush),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Navigation back"
                    )
                }
            }



            AsyncImage(
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .clip(CircleShape),
                model = user?.avatarUrl,
                contentDescription = "User Image",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = user?.name ?: "",
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "@ ${user?.login} ",
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleMedium,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (user?.location != null)
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = "User Location Icon",
                    )
                Text(
                    text = user?.location ?: "",
                    modifier = Modifier
                        .padding(4.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.titleSmall,
                )
            }



            Spacer(modifier = Modifier.height(32.dp))


        }

        Box(Modifier.padding(horizontal = 16.dp)) {
            Card(
                elevation = customCardElevation
            ) {
                UserDetailsSubHeader(
                    user = user,
                    onReposItemClick = onReposItemClick,
                    onGistsItemClick = onGistsItemClick,
                    onFollowersItemClick = onFollowersItemClick,
                    onFollowingItemClick = onFollowingItemClick,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserDetailsHeaderPreview() {
    UserDetailsHeader(
        user = UserModel(
            name = "Ravi Kumar Badini",
            login = "rvkmr3210",
            location = "Kukatpally, Hyderabad",
        ),
        {}, {}, {}, {}, {}
    )
}
