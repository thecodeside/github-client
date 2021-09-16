package com.thecodeside.githubclient.features.trendingrepos

import app.cash.turbine.test
import com.thecodeside.githubclient.feature.trendingrepos.FetchTrendingRepositoriesUseCase
import com.thecodeside.githubclient.feature.trendingrepos.TrendingRepositoriesViewModel
import com.thecodeside.githubclient.feature.trendingrepos.TrendingRepositoriesViewState
import com.thecodeside.githubclient.utils.TestDispatcherExtension
import com.thecodeside.githubclient.utils.mockEmptyTrendingRepositories
import com.thecodeside.timberjunit5.TimberTestExtension
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExtendWith(TimberTestExtension::class)
internal class TrendingRepositoriesViewModelTest {

    @JvmField
    @RegisterExtension
    val testDispatcherExtension = TestDispatcherExtension()

    private val fetchTrendingRepositoriesUseCase: FetchTrendingRepositoriesUseCase = mockk()

    @BeforeEach
    fun beforeEach() {
        clearAllMocks()
        coEvery { fetchTrendingRepositoriesUseCase() } returns mockEmptyTrendingRepositories
    }

    private lateinit var viewModel: TrendingRepositoriesViewModel

    @Nested
    inner class `GIVEN view state test` {

        /*
            Unfortunately flow can't emit in tests before turibine start to listen.
            In our case we emit item in ViewModel init function and
            that's why in our tests we starts from isLoading should be false.
            https://github.com/cashapp/turbine#hot-flows
        */
        @Test
        fun `WHEN fetch trending repositories data THEN state starts loading, and finally stops loading`() =
            testDispatcherExtension.runBlockingTest {
                viewModel = provideViewModel()
                viewModel.viewState.test {
                    viewModel.fetchData()
                    awaitItem().isLoading.shouldBeFalse() // after fetch from init()
                    awaitItem().isLoading.shouldBeTrue()
                    awaitItem().isLoading.shouldBeFalse()
                    expectNoEvents()
                }
            }

        // TODO: 07.08.2021 redesign ViewModel to enable Shared Flow testing
        @Disabled("Disabled because we can't test shared flow when it emits item's in init function. Needs to be redesigned.")
        @Test
        fun `WHEN fetch trending repositories data throws Exception THEN error is shown`() =
            testDispatcherExtension.runBlockingTest {
                viewModel.viewEffect.test {
                    viewModel.fetchData()
                    awaitItem().shouldBeInstanceOf<TrendingRepositoriesViewState>()
                    expectNoEvents()
                }
            }
    }

    private fun provideViewModel() = TrendingRepositoriesViewModel(
        fetchTrendingRepositoriesUseCase,
        testDispatcherExtension.testDispatchers
    )
}