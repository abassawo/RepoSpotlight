package com.lindenlabs.repospotlight.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lindenlabs.repospotlight.data.AppDataSource
import com.lindenlabs.repospotlight.data.models.RepoModel
import javax.inject.Inject

class SpotlightReposPagingSource @Inject constructor(val appDataSource: AppDataSource) :
    PagingSource<Int, RepoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        return try {
            val nextPageNumber = params.key ?: 0
            val repos = appDataSource.getPopularRepos(nextPageNumber)
            LoadResult.Page(
                data = repos,
                prevKey = if (nextPageNumber == 0) null else nextPageNumber - 1,
                nextKey = if (repos.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return null
    }
}