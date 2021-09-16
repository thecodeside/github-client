package com.thecodeside.githubclient.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thecodeside.githubclient.common.ui.theme.Gray30
import com.thecodeside.githubclient.common.ui.theme.ProgressSize

@Composable
fun ProgressIndicator() {
    // TODO: 06.08.2021 ripple effect should be turned off 
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Gray30)
            .fillMaxSize()
            .clickable {},
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .defaultMinSize(ProgressSize, ProgressSize)
        )
    }
}