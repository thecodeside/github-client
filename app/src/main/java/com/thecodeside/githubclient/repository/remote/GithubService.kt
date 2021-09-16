package com.thecodeside.githubclient.repository.remote

import com.thecodeside.githubclient.repository.model.TrendingRepositories
import retrofit2.http.GET

interface GithubService {

    @GET("repo")
    suspend fun getTrendingRepositories(): TrendingRepositories
}
