package com.thecodeside.githubclient.feature.trendingrepos


import android.content.res.Resources
import androidx.annotation.StringRes
import com.thecodeside.githubclient.R
import com.thecodeside.githubclient.repository.model.RepositoryDetails

data class TrendingRepositoriesViewState(
    val isLoading: Boolean = true,
    val repositories: List<RepositoryDetails> = emptyList(),
)

sealed class TrendingRepositoriesViewEffect {

    data class Error(
        private val messageString: String? = null,
        @StringRes
        private val messageRes: Int? = null,
    ) : TrendingRepositoriesViewEffect() {
        fun parseErrorMessage(resources: Resources) = messageString
            ?: messageRes?.let { resources.getString(it) }
            ?: resources.getString(R.string.unknown_error)
    }
}
