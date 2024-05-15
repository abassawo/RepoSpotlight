package com.lindenlabs.repospotlight.data.api

import com.lindenlabs.repospotlight.data.models.Contributor
import com.lindenlabs.repospotlight.data.models.RepoModel

interface AppDataSource {
    suspend fun getPopularRepos(page: Int): List<RepoModel>

    suspend fun getTopContributors(repoModel: RepoModel): List<Contributor>

}