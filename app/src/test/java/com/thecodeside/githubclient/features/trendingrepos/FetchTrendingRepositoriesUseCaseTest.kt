package com.thecodeside.githubclient.features.trendingrepos

import com.thecodeside.githubclient.feature.trendingrepos.FetchTrendingRepositoriesUseCase
import com.thecodeside.githubclient.repository.GithubRepository
import com.thecodeside.githubclient.utils.TestDispatcherExtension
import com.thecodeside.githubclient.utils.mockEmptyTrendingRepositories
import com.thecodeside.timberjunit5.TimberTestExtension
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

// TODO: 06.08.2021 currently we test only mocking because lack of logic in use case
@ExtendWith(TimberTestExtension::class)
internal class FetchTrendingRepositoriesUseCaseTest {

    @JvmField
    @RegisterExtension
    val testDispatcherExtension = TestDispatcherExtension()

    private val cvRepository: GithubRepository = mockk {
        coEvery { fetchTrendingRepositories() } returns mockEmptyTrendingRepositories
    }

    private val fetchTrendingRepositoriesUseCase = FetchTrendingRepositoriesUseCase(cvRepository)

    @Test
    fun `WHEN trending repositories data is fetched THEN trending repositories data is returned`() =
        testDispatcherExtension.runBlockingTest {
            val receivedData = fetchTrendingRepositoriesUseCase()
            receivedData shouldBe mockEmptyTrendingRepositories
        }
}