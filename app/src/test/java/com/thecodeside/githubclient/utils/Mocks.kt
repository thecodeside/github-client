package com.thecodeside.githubclient.utils

import com.thecodeside.githubclient.repository.model.TrendingRepositories


val mockEmptyTrendingRepositories = TrendingRepositories(
    count = 0,
    items = emptyList(),
    msg = ""
)