package com.thecodeside.githubclient.repository.remote

import javax.inject.Inject


class RemoteGithubDataSource @Inject constructor(
    private val githubService: GithubService
) {

    suspend fun fetchTrendingRepositories() = githubService.getTrendingRepositories()
}
