package com.rrm.rvkmr.ui.components.userDetails;

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsBodyItem(heading: String, details: String?, icon: ImageVector) {

    ListItem(
        headlineText = {
            Text(
                text = details ?: "N/A",
                Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )
        },
        leadingContent = {
            Icon(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                imageVector = icon,
                contentDescription = "User $heading Image",
            )
        },
        overlineText = {
            Text(
                text = heading,
                Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall
            )
        })

}

@Preview(showSystemUi = false)
@Composable
fun DefaultUserDetailsBodyItemPreview() {
    UserDetailsBodyItem(
        heading = "Email",
        details = "rvkmr3210@gmail.com",
        icon = Icons.Default.Email
    )
}
