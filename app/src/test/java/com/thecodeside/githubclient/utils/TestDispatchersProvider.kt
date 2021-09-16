package com.thecodeside.githubclient.utils

import com.thecodeside.githubclient.common.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher


class TestDispatchersProvider(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : DispatcherProvider {

    override fun default(): CoroutineDispatcher = testDispatcher
    override fun io(): CoroutineDispatcher = testDispatcher
    override fun main(): CoroutineDispatcher = testDispatcher
    override fun unconfined(): CoroutineDispatcher = testDispatcher
}