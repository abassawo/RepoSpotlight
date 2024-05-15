package com.lindenlabs.repospotlight.screens.main

import com.lindenlabs.repospotlight.data.models.RepoModel

data class SpotlightRepoViewEntity(
    val repoModel: RepoModel,
    val isRelatedToAndroid: Boolean,
    val clickAction: (repoModel: RepoModel) -> Unit
)