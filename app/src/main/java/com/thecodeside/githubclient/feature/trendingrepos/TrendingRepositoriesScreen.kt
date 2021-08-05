package com.thecodeside.githubclient.feature.trendingrepos

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thecodeside.githubclient.R
import com.thecodeside.githubclient.repository.model.GithubRepository
import com.thecodeside.githubclient.ui.theme.ProgressSize

@Composable
fun TrendingRepositoriesScreen(viewModel: TrendingRepositoriesViewModel) {
    val state = viewModel.state.collectAsState()
    TrendingRepositoriesContent(state, viewModel::onRepositoryClicked)
}

@Composable
fun TrendingRepositoriesContent(
    viewState: State<TrendingRepositoriesViewState>,
    onItemClickListener: (GithubRepository) -> Unit
) {
    Scaffold(
        topBar = { GithubClientAppBar() },
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (viewState.value) {
            is TrendingRepositoriesViewState.Loading -> TrendingRepositoriesLoading()
            is TrendingRepositoriesViewState.Data -> {
                TrendingRepositoriesData(
                    (viewState.value as TrendingRepositoriesViewState.Data).repositories,
                    onItemClickListener
                )
            }
            is TrendingRepositoriesViewState.EmptyList -> TrendingRepositoriesEmpty()
        }
    }
}

@Composable
fun TrendingRepositoriesEmpty() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.no_items),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun TrendingRepositoriesLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .defaultMinSize(ProgressSize, ProgressSize)
        )
    }
}

@Composable
fun GithubClientAppBar() {
    TopAppBar(
        elevation = 6.dp,
        modifier = Modifier.height(58.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewTrendingRepositories() {
    val state = derivedStateOf { TrendingRepositoriesViewState.EmptyList }
    TrendingRepositoriesContent(state, {})
}
