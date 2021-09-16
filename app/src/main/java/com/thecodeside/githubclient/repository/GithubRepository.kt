package com.thecodeside.githubclient.repository

import com.thecodeside.githubclient.repository.local.LocalGithubDataSource
import com.thecodeside.githubclient.repository.model.TrendingRepositories
import com.thecodeside.githubclient.repository.remote.RemoteGithubDataSource
import javax.inject.Inject

// TODO: 07.08.2021  split repository between features
class GithubRepository @Inject constructor(
    private val localGithubDataSource: LocalGithubDataSource,
    private val remoteGithubDataSource: RemoteGithubDataSource
) {

    suspend fun fetchTrendingRepositories(): TrendingRepositories =
        remoteGithubDataSource.fetchTrendingRepositories()
}
