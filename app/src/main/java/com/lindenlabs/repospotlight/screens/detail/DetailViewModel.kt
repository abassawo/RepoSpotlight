package com.lindenlabs.repospotlight.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.repospotlight.data.api.AppDataSource
import com.lindenlabs.repospotlight.data.models.RepoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val appDataSource: AppDataSource) : ViewModel() {
    lateinit var repo: RepoModel
    private val mutableViewState =
        MutableStateFlow<DetailContract.ViewState>(DetailContract.ViewState.Loading)
    val viewState: StateFlow<DetailContract.ViewState> = mutableViewState
    fun init(repoModel: RepoModel) {
        this.repo = repoModel
        viewModelScope.launch {
            runCatching { appDataSource.getTopContributors(repoModel) }
                .onSuccess { contributors ->
                    Timber.d("Response contributors:" + contributors)
                    mutableViewState.value = DetailContract.ViewState.Content(contributors)
                }
                .onFailure {
                    Timber.e(it)
                    mutableViewState.value = DetailContract.ViewState.Failure
                }
        }

    }
}