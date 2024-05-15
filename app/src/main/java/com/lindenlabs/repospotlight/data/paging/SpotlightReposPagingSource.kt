package com.lindenlabs.repospotlight.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lindenlabs.repospotlight.data.api.AppDataSource
import com.lindenlabs.repospotlight.data.models.RepoModel
import javax.inject.Inject

class SpotlightReposPagingSource @Inject constructor(val appDataSource: AppDataSource) :
    PagingSource<Int, RepoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        return try {
            val pageNumber = params.key ?: 0
            val repos = appDataSource.getPopularRepos(pageNumber)
            LoadResult.Page(
                data = repos,
                prevKey = if (pageNumber == 0) null else pageNumber - 1,
                nextKey = if (repos.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return null
    }
}