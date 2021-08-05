package com.thecodeside.githubclient.repository.local

import com.thecodeside.githubclient.repository.model.GithubRepository
import javax.inject.Inject

// TODO: 07/31/21 implement local data source
class LocalGithubDataSource @Inject constructor() {

    fun fetchTrendingRepositories(): List<GithubRepository> = TODO("implement local data source")
}
