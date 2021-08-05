package com.thecodeside.githubclient.feature.trendingrepos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thecodeside.githubclient.common.utils.DispatcherProvider
import com.thecodeside.githubclient.repository.model.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class TrendingRepositoriesViewModel @Inject constructor(
    private val fetchTrendingRepositories: FetchTrendingRepositoriesUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _state =
        MutableStateFlow<TrendingRepositoriesViewState>(TrendingRepositoriesViewState.Loading)
    val state = _state.asStateFlow()

    private val _viewEffect = MutableSharedFlow<TrendingRepositoriesViewEffect>()
    val viewEffect = _state.asSharedFlow()

    init {
        viewModelScope.launch(dispatchers.io()) {
            try {
                val repos = fetchTrendingRepositories()
                _state.value = TrendingRepositoriesViewState.Data(repositories = repos.items)
                println(repos)
            } catch (e: Exception) {
                Timber.e(e)
                _viewEffect.tryEmit(TrendingRepositoriesViewEffect.ErrorLoadingDataViewEffect)
            }
        }
    }

    fun onRepositoryClicked(githubRepository: GithubRepository) {
        _viewEffect.tryEmit(
            TrendingRepositoriesViewEffect.RepositoryClickedViewEffect(
                githubRepository
            )
        )
    }
}
