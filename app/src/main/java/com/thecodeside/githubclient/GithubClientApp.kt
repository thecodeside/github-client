package com.thecodeside.githubclient

import android.app.Application
import com.thecodeside.githubclient.common.utils.TimberInitializer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class GithubClientApp : Application() {

    override fun onCreate() {
        super.onCreate()
        TimberInitializer.init()
    }
}
