package com.rrm.rvkmr.ui.components.userDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(.34f),
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = customCardElevation
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(CircleShape),
                    model = user?.avatarUrl,
                    contentDescription = "User Image",
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = user?.name ?: "",
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }


        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserDetailsHeaderPreview() {
    UserDetailsHeader(user = UserModel(name = "Ravi Kumar Badini"))
}
