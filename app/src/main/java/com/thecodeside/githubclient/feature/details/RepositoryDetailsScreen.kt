package com.thecodeside.githubclient.feature.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thecodeside.githubclient.common.ui.GithubClientAppBar
import com.thecodeside.githubclient.repository.model.RepositoryDetails

const val REPOSITORY_BUNDLE_ARG = "repository_bundle_arg"

@Composable
fun RepositoryDetailsScreen(
    repository: RepositoryDetails?,
) {
    RepositoryDetailsContent(repository)
}

@Composable
fun RepositoryDetailsContent(repository: RepositoryDetails?) {
    Scaffold(
        topBar = { GithubClientAppBar() },
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("$repository")
    }
}
