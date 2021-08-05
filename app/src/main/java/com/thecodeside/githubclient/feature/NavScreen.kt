package com.thecodeside.githubclient.feature

sealed class NavScreen(val route: String) {

    object RepoList : NavScreen("Home")

    object RepoDetails : NavScreen("RepoDetails") {

        const val routeWithArgument: String = "RepoDetails/{repoId}"

        const val repoId: String = "repoId"
    }
}