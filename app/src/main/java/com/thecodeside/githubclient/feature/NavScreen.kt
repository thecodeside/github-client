package com.thecodeside.githubclient.feature

sealed class NavScreen(val route: String) {

    object TrendingRepositories : NavScreen("TrendingRepositories")

    object RepositoryDetails : NavScreen("RepositoryDetails")
}