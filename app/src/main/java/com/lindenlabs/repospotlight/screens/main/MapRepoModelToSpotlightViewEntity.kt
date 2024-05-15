package com.lindenlabs.repospotlight.screens.main

import com.lindenlabs.repospotlight.data.models.RepoModel
import javax.inject.Inject

class MapRepoModelToSpotlightViewEntity @Inject constructor() {

    operator fun invoke(
        repositories: List<RepoModel>,
        clickAction: (repoModel: RepoModel) -> Unit
    ): List<SpotlightRepoViewEntity> {
        return repositories.map { it.toViewEntity(clickAction) }
    }

    private fun RepoModel.toViewEntity(clickAction: (repoModel: RepoModel) -> Unit): SpotlightRepoViewEntity {
        return SpotlightRepoViewEntity(
            this,
            isRelatedToAndroid = topics?.isAndroidRelated() == true,
            clickAction = clickAction
        )
    }

    private fun List<String>.isAndroidRelated(): Boolean {
        return this.contains("Android") || this.contains("android")
    }
}



