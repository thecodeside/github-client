package com.thecodeside.githubclient.feature.trendingrepos

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.thecodeside.githubclient.R
import com.thecodeside.githubclient.common.ui.GithubClientAppBar
import com.thecodeside.githubclient.common.ui.ProgressIndicator
import com.thecodeside.githubclient.feature.NavScreen
import com.thecodeside.githubclient.feature.details.REPOSITORY_BUNDLE_ARG
import com.thecodeside.githubclient.repository.model.RepositoryDetails
import kotlinx.coroutines.flow.collect

@Composable
fun TrendingRepositoriesScreen(
    viewModel: TrendingRepositoriesViewModel,
    navController: NavHostController,
) {
    val viewState = viewModel.viewState.collectAsState()
    val context = LocalContext.current

    // TODO: 06.08.2021 Add porper side effects handling for Jetpack Compose
    LocalLifecycleOwner.current.lifecycleScope.launchWhenStarted {
        viewModel.viewEffect.collect {
            renderViewEffect(it, context)
        }
    }

    TrendingRepositoriesContent(
        viewState,
        // TODO: 06.08.2021 navigation could be handled by VM through proper side effects
        {
            navController.currentBackStackEntry?.arguments =
                Bundle().apply {
                    putParcelable(REPOSITORY_BUNDLE_ARG, it)
                }
            navController.navigate(NavScreen.RepositoryDetails.route)
        },
        viewModel::fetchData
    )
}

@Composable
fun TrendingRepositoriesContent(
    viewState: State<TrendingRepositoriesViewState>,
    onItemClickListener: (RepositoryDetails) -> Unit,
    onRefresh: () -> Unit,
) {
    Scaffold(
        topBar = { GithubClientAppBar() },
        modifier = Modifier
            .fillMaxSize()
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(false),
            onRefresh = onRefresh,
        ) {
            RenderViewState(viewState, onItemClickListener, onRefresh)
        }
    }
}


@Composable
private fun RenderViewState(
    viewState: State<TrendingRepositoriesViewState>,
    onItemClickListener: (RepositoryDetails) -> Unit,
    onRefresh: () -> Unit,
) {
    TrendingRepositoriesList(
        viewState.value.repositories,
        onItemClickListener
    )
    if (viewState.value.repositories.isEmpty() && !viewState.value.isLoading) {
        RenderEmptyView(onRefresh)
    }
    if (viewState.value.isLoading) {
        ProgressIndicator()
    }
}

@Composable
private fun RenderEmptyView(onRefresh: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = onRefresh
        ) {
            Text(stringResource(id = R.string.refresh))
        }
    }
}

private fun renderViewEffect(
    viewEffect: TrendingRepositoriesViewEffect,
    context: Context
) {
    when (viewEffect) {
        // TODO: 06.08.2021 should be handled by snackbar when proper side effects will be handled
        is TrendingRepositoriesViewEffect.Error -> {
            Toast.makeText(
                context,
                viewEffect.parseErrorMessage(context.resources),
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}

@Preview
@Composable
fun PreviewTrendingRepositories() {
    val state = derivedStateOf { TrendingRepositoriesViewState() }
    TrendingRepositoriesContent(state, {}, {})
}
