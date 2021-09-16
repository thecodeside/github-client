package com.thecodeside.githubclient.feature.trendingrepos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thecodeside.githubclient.R
import com.thecodeside.githubclient.repository.model.RepositoryDetails

@Composable
fun TrendingRepositoriesList(
    repositories: List<RepositoryDetails>,
    onItemClickListener: (RepositoryDetails) -> Unit,
) {
    // TODO: 06.08.2021 - Restrict max width for better look on tablet or portrait mode
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(
            items = repositories,
            itemContent = { index, item ->
                RepositoryItem(repository = item, onItemClickListener)
                if (index < repositories.size - 1)
                    Divider(
                        color = Color.Transparent,
                        thickness = 8.dp
                    )
            },
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RepositoryItem(
    repository: RepositoryDetails,
    onItemClickListener: (RepositoryDetails) -> Unit
) {
    Card(
        onClick = {
            onItemClickListener(repository)
        },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = repository.repo,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = repository.desc,
                style = MaterialTheme.typography.subtitle2
            )
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_star_rate_24),
                    contentDescription = stringResource(R.string.accessibility_star),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                )
                Text(
                    text = repository.stars,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTrendingRepositoriesData() {
    val repository = RepositoryDetails(
        addedStars = "12",
        avatars = emptyList<String>(),
        desc = "Description",
        forks = "31",
        lang = "Java",
        repo = "Repo name",
        repoLink = "www.link.com",
        stars = "31"
    )
    TrendingRepositoriesList(repositories = listOf(repository, repository, repository)) {}
}