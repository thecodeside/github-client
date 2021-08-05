package com.thecodeside.githubclient.repository.remote

import com.thecodeside.githubclient.repository.model.TrendingRepositories
import retrofit2.http.GET

interface GithubService {

    @GET("repo")
    suspend fun getTrendingRepositories(
//        @Query("language") language: String? = null,
//        @Query("since") since: String? = null,
//        @Query("spoken_language_code") spokenLanguageCode: String? = null
    ): TrendingRepositories
}
