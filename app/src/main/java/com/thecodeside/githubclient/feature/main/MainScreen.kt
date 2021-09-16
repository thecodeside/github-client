package com.thecodeside.githubclient.feature.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.thecodeside.githubclient.feature.NavScreen
import com.thecodeside.githubclient.feature.details.REPOSITORY_BUNDLE_ARG
import com.thecodeside.githubclient.feature.details.RepositoryDetailsScreen
import com.thecodeside.githubclient.feature.trendingrepos.TrendingRepositoriesScreen
import com.thecodeside.githubclient.feature.trendingrepos.TrendingRepositoriesViewModel
import com.thecodeside.githubclient.repository.model.RepositoryDetails

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    ProvideWindowInsets {
        NavHost(
            navController = navController,
            startDestination = NavScreen.TrendingRepositories.route
        ) {
            composable(NavScreen.TrendingRepositories.route) {
                val viewModel = hiltViewModel<TrendingRepositoriesViewModel>()
                TrendingRepositoriesScreen(
                    viewModel,
                    navController,
                )
            }
            composable(NavScreen.RepositoryDetails.route) {
                /*
                Workaround to pass parcelable in Jetpack Compose
                https://medium.com/codechai/passing-parcelable-serializable-in-jetpack-compose-navigation-bf398d851371
                 */
                val repository =
                    navController.previousBackStackEntry?.arguments?.getParcelable<RepositoryDetails>(
                        REPOSITORY_BUNDLE_ARG
                    )

                RepositoryDetailsScreen(
                    repository
                )
            }
        }
    }
}
