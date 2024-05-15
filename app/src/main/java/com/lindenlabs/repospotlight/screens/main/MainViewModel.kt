package com.lindenlabs.repospotlight.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.lindenlabs.repospotlight.data.models.SpotlightRequest
import com.lindenlabs.repospotlight.data.paging.SpotlightReposPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val spotlightPagingSource: SpotlightReposPagingSource,
    val mapElements: MapRepoModelToSpotlightViewEntity
) : ViewModel() {
    private val mutableViewState: MutableStateFlow<MainScreenContract.ViewState> = MutableStateFlow(
        MainScreenContract.ViewState.Loading
    )
    val viewState: StateFlow<MainScreenContract.ViewState> = mutableViewState
    val viewEntities: MutableList<SpotlightRepoViewEntity> = mutableListOf()

    init {
        fetchTopRepos()
    }

    fun fetchTopRepos() {
        if (viewEntities.size < 100) {
            viewModelScope.launch() {
                runCatching {
                    spotlightPagingSource.load(
                        PagingSource.LoadParams.Refresh(
                            key = request.page,
                            loadSize = request.perPage,
                            placeholdersEnabled = true
                        )
                    )
                }.mapCatching { (it as? PagingSource.LoadResult.Page)?.data ?: emptyList() }
                    .mapCatching { mapElements(it) }
                    .onSuccess { repoViewEntities ->
                        viewEntities.addAll(repoViewEntities)
                        Timber.d("Repositories retrieved ${repoViewEntities.size}")
                        Timber.d("Repositories to be emitted ${viewEntities.size}")
                        mutableViewState.value =
                            MainScreenContract.ViewState.Spotlight(viewEntities)
                    }
                    .onFailure {
                        Timber.e("An error occurred $it")
                        mutableViewState.value = MainScreenContract.ViewState.ErrorState(it)
                    }

                request = request.copy(
                    page = request.page + 1,
                    perPage = 25
                ) // Increment for additional results
            }
        } else {
            request = request.copy(page = 0, perPage = 25)
//            fetchTopRepos()
        }
    }

    companion object {
        var request = SpotlightRequest(1, 25)
    }
}
