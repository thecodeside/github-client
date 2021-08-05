package com.thecodeside.githubclient.repository.model


import kotlinx.serialization.Serializable

@Serializable
data class TrendingRepositories(
    val count: Int,
    val items: List<GithubRepository>,
    val msg: String
)