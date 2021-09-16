package com.thecodeside.githubclient.feature.trendingrepos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecodeside.githubclient.R
import com.thecodeside.githubclient.common.utils.DispatcherProvider
import com.thecodeside.githubclient.common.utils.isNetworkFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class TrendingRepositoriesViewModel @Inject constructor(
    private val fetchTrendingRepositories: FetchTrendingRepositoriesUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _viewState =
        MutableStateFlow(TrendingRepositoriesViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEffect = MutableSharedFlow<TrendingRepositoriesViewEffect>()
    val viewEffect = _viewEffect.asSharedFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        _viewState.value = viewState.value.copy(isLoading = true)
        viewModelScope.launch(dispatchers.io()) {
            val newState = try {
                val repos = fetchTrendingRepositories()
                TrendingRepositoriesViewState(isLoading = false, repos.items)
            } catch (e: Exception) {
                ensureActive()
                Timber.e(e)
                parseError(e)
                viewState.value.copy(isLoading = false)
            }
            withContext(dispatchers.main()) {
                _viewState.value = newState
            }
        }
    }

    private fun parseError(e: Exception) = if (e.isNetworkFailure()) {
        TrendingRepositoriesViewEffect.Error(messageRes = R.string.unknown_error)
    } else {
        TrendingRepositoriesViewEffect.Error(messageRes = R.string.network_error)
    }

}
