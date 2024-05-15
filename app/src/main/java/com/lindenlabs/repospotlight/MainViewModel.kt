package com.lindenlabs.repospotlight

import android.view.ViewStub
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.repospotlight.data.AppDataSource
import com.lindenlabs.repospotlight.screens.MainScreenContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val appDataSource: AppDataSource) : ViewModel() {
    private val mutableViewState: MutableStateFlow<MainScreenContract.ViewState> = MutableStateFlow(MainScreenContract.ViewState.Loading)
    val viewState: StateFlow<MainScreenContract.ViewState> = mutableViewState

    init {
        fetchTopRepos()
    }
    fun fetchTopRepos() {
       viewModelScope.launch() {
           val repositories = appDataSource.getPopularRepos()
           Timber.d("Repositories $repositories")
           mutableViewState.value = MainScreenContract.ViewState.Spotlight(repositories)
       }
    }
}
