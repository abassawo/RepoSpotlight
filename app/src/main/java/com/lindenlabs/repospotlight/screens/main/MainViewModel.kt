package com.lindenlabs.repospotlight.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.repospotlight.data.AppDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val appDataSource: AppDataSource,
    val mapElements: MapRepoModelToSpotlightViewEntity
) : ViewModel() {
    private val mutableViewState: MutableStateFlow<MainScreenContract.ViewState> = MutableStateFlow(
        MainScreenContract.ViewState.Loading
    )
    val viewState: StateFlow<MainScreenContract.ViewState> = mutableViewState

    init {
        fetchTopRepos()
    }

    fun fetchTopRepos() {
        viewModelScope.launch() {
            runCatching { appDataSource.getPopularRepos() }
                .mapCatching { mapElements(it) }
                .onSuccess { repoViewEntities ->
                    Timber.d("Repositories retrieved $repoViewEntities")
                    mutableViewState.value =
                        MainScreenContract.ViewState.Spotlight(repoViewEntities)
                }.onFailure {
                    Timber.e("An error occurred $it")
                    mutableViewState.value = MainScreenContract.ViewState.ErrorState(it)
                }
        }
    }
}
