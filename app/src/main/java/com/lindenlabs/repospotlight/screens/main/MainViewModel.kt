package com.lindenlabs.repospotlight.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.lindenlabs.repospotlight.data.models.SpotlightRequest
import com.lindenlabs.repospotlight.data.paging.SpotlightReposPagingSource
import com.lindenlabs.repospotlight.screens.main.MainScreenContract.ViewEvent
import com.lindenlabs.repospotlight.screens.main.MainScreenContract.ViewEvent.NavigateToDetailScreen
import com.lindenlabs.repospotlight.screens.main.MainScreenContract.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val dispatcher: CoroutineDispatcher,
    val spotlightPagingSource: SpotlightReposPagingSource,
    val mapElements: MapRepoModelToSpotlightViewEntity
) : ViewModel() {
    private val mutableViewState: MutableStateFlow<ViewState> = MutableStateFlow(
        ViewState.Loading
    )
    val viewState: StateFlow<ViewState> = mutableViewState
    val viewEntities: MutableList<SpotlightRepoViewEntity> = mutableListOf()

    private val mutableViewEvent: MutableStateFlow<ViewEvent?> =
        MutableStateFlow(
            null
        )
    val viewEvent: StateFlow<ViewEvent?> =
        mutableViewEvent

    init {
        fetchTopRepos()
    }

    fun fetchTopRepos() {
        if (viewEntities.size < 100) {
            viewModelScope.launch(dispatcher) {
                val params = PagingSource.LoadParams.Refresh(
                    key = request.page,
                    loadSize = request.perPage,
                    placeholdersEnabled = true
                )

                runCatching {
                    spotlightPagingSource.load(params)
                }.mapCatching { (it as? PagingSource.LoadResult.Page)?.data ?: emptyList() }
                    .mapCatching { mapElements(it) }
                    .onSuccess { repoViewEntities ->
                        viewEntities.addAll(repoViewEntities)
                        Timber.d("Repositories retrieved ${repoViewEntities.size}")
                        Timber.d("Repositories to be emitted ${viewEntities.size}")

                        if (viewEntities.isEmpty()) {
                            mutableViewState.value =
                                ViewState.Empty
                        } else {
                            mutableViewState.value =
                                ViewState.Spotlight(viewEntities)
                        }
                    }
                    .onFailure {
                        Timber.e("An error occurred $it")
                        mutableViewState.value = MainScreenContract.ViewState.ErrorState(it)
                    }
                request = request.copy(
                    page = request.page + 1,
                    perPage = 25
                )
            }
        } else {
            request = request.copy(page = 0, perPage = 25)
        }
    }

    fun handleInteraction(interaction: MainScreenContract.Interaction) =
        when (interaction) {
            is MainScreenContract.Interaction.SpotlightRepoClicked -> {
                mutableViewEvent.value = NavigateToDetailScreen(interaction.repoModel)
            }
        }

    companion object {
        var request = SpotlightRequest(1, 25)
    }
}
