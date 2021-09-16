package com.thecodeside.githubclient.repository.local

import com.thecodeside.githubclient.repository.model.RepositoryDetails
import javax.inject.Inject

// TODO: 07.08.2021 implement local data source
class LocalGithubDataSource @Inject constructor() {

    fun fetchTrendingRepositories(): List<RepositoryDetails> = TODO("implement local data source")
}
