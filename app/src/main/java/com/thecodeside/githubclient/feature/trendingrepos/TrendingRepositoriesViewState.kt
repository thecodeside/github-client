package com.thecodeside.githubclient.feature.trendingrepos


import com.thecodeside.githubclient.repository.model.GithubRepository

sealed class TrendingRepositoriesViewState {

    object Loading : TrendingRepositoriesViewState()
    data class Data(val repositories: List<GithubRepository>) : TrendingRepositoriesViewState()
    object EmptyList : TrendingRepositoriesViewState()
}

sealed class TrendingRepositoriesViewEffect {

    object ErrorLoadingDataViewEffect : TrendingRepositoriesViewEffect()
    data class RepositoryClickedViewEffect(
        val repo: GithubRepository
    ) : TrendingRepositoriesViewEffect()
}
