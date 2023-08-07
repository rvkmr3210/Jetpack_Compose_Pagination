package com.rvkmr.pagination.ui.components.userDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserDetailsSubHeaderItem(heading: String, details: String?, onItemClick: () -> Unit) {

    Box(
        modifier = Modifier
            .clickable(onClick = onItemClick),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = details.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = heading,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }

}

@Preview
@Composable
fun UserDetailsSubHeaderItemPreview() {
    UserDetailsSubHeaderItem("Public Repos", "0", {})
}