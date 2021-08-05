package com.thecodeside.githubclient.feature.trendingrepos

import com.thecodeside.githubclient.repository.GithubRepository
import javax.inject.Inject

class FetchTrendingRepositoriesUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
) {

    suspend operator fun invoke() = githubRepository.fetchTrendingRepositories()
}
