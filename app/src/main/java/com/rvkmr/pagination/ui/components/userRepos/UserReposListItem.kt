package com.rvkmr.pagination.ui.components.userRepos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.android.style.TextDecorationSpan
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rvkmr.pagination.data.models.UserRepoModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserReposListItem(repo: UserRepoModel) {

    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

        Card(elevation = CardDefaults.cardElevation(8.dp)) {
            ListItem(
                headlineText = {
                    Text(
                        text = repo.name ?: "",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                leadingContent = {

                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .fillMaxHeight()
                            .background(
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = repo.name?.substring(0, 1)?.capitalize(Locale.ROOT) ?: "",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primaryContainer,
                        )
                    }

                },
                overlineText = {

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        shadowElevation = 2.dp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ) {
                        Text(
                            text = repo.language ?: "",
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 8.dp),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                },
                supportingText = {
                    Text(
                        text = repo.description ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            )
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun UserReposListItemPreview() {
    UserReposListItem(
        UserRepoModel(
            name = "Jetpack Compose Pagination",
            language = "Kotlin",
            description = "sample project demonstrates how to implement pagination in a Jetpack Compose app using GitHub's public APIs."
        )
    )
}