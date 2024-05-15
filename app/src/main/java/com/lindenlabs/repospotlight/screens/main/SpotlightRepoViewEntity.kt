package com.lindenlabs.repospotlight.screens.main

import com.lindenlabs.repospotlight.data.models.RepoModel

data class SpotlightRepoViewEntity(
    val repoName: String,
    val description: String,
    val starGazersText: String,
    val repoModel: RepoModel,
    val topics: List<String> = repoModel.topics ?: emptyList(),
    val isRelatedToAndroid: Boolean
)