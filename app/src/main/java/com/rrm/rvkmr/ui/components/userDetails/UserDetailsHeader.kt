package com.rrm.rvkmr.ui.components.userDetails

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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rrm.rvkmr.data.models.UserModel

@Composable
fun UserDetailsHeader(user: UserModel?) {

    val customCardElevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp, pressedElevation = 2.dp, focusedElevation = 4.dp
    )

    val colorStops = arrayOf(
        0f to MaterialTheme.colorScheme.primary,
        1f to MaterialTheme.colorScheme.onPrimary
    )
    val brush = Brush.linearGradient(colorStops = colorStops)


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
            Spacer(modifier = Modifier.height(24.dp))

            AsyncImage(
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
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
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "User Location Icon",
                )
                Text(
                    text = user?.location ?: "",
                    modifier = Modifier
                        .padding(4.dp),
                    style = MaterialTheme.typography.titleMedium,
                )
            }



            Spacer(modifier = Modifier.height(32.dp))


        }

        Box(Modifier.padding(horizontal = 16.dp)) {
            Card(
                elevation = customCardElevation
            ) {
                UserDetailsSubHeader(user = user)
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
            location = "Kukatpally, Hyderabad"
        )
    )
}
