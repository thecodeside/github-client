package com.thecodeside.githubclient.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepository(
    @SerialName("added_stars")
    val addedStars: String,
    val avatars: List<String>,
    val desc: String,
    val forks: String,
    val lang: String,
    val repo: String,
    @SerialName("repo_link")
    val repoLink: String,
    val stars: String
)