package com.thecodeside.githubclient.feature.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.thecodeside.githubclient.feature.NavScreen
import com.thecodeside.githubclient.feature.trendingrepos.TrendingRepositoriesScreen
import com.thecodeside.githubclient.feature.trendingrepos.TrendingRepositoriesViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.RepoList.route) {
            composable(NavScreen.RepoList.route) {
                val viewModel = hiltViewModel<TrendingRepositoriesViewModel>()
                TrendingRepositoriesScreen(
                    viewModel
                )
            }
        }
    }
}

