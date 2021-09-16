package com.thecodeside.githubclient.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: 07.08.2021 we can split remote data, and data passed to UI
@Parcelize
@Serializable
data class RepositoryDetails(
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
) : Parcelable