package com.thecodeside.githubclient.repository

import com.thecodeside.githubclient.repository.local.LocalGithubDataSource
import com.thecodeside.githubclient.repository.remote.RemoteGithubDataSource
import com.thecodeside.githubclient.utils.TestDispatcherExtension
import com.thecodeside.githubclient.utils.mockEmptyTrendingRepositories
import com.thecodeside.timberjunit5.TimberTestExtension
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@ExtendWith(TimberTestExtension::class)
internal class GithubRepositoryTest {

    @JvmField
    @RegisterExtension
    val testDispatcherExtension = TestDispatcherExtension()

    private val localGithubDataSource: LocalGithubDataSource = mockk()
    private val remoteGithubDataSource: RemoteGithubDataSource = mockk {
        coEvery { fetchTrendingRepositories() } returns mockEmptyTrendingRepositories
    }

    private val githubRepository = GithubRepository(localGithubDataSource, remoteGithubDataSource)

    @Test
    fun `WHEN trending repositories are fetched THEN trending repository data is returned direct from remote server`() =
        testDispatcherExtension.runBlockingTest {
            val receivedData = githubRepository.fetchTrendingRepositories()

            assertAll(
                { receivedData shouldBe mockEmptyTrendingRepositories },
                { verify { localGithubDataSource wasNot Called } }
            )
        }
}